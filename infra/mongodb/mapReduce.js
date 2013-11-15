var map_function_version = function () {
    var key = {
        org: this.org,
        name: this.name,
        type: this.type,
        status: this.status
    };
    var value = {
        version: this.version,
        isForcedVersion: this.isForcedVersion
    };
    emit(key, value);
}

var reduce_function_version = function (key, values) {

    var maxVersionDoc;
    var curMaxVersionValTab = [];
    var BreakException = {};
    try {
        values.forEach(
            function (curObj) {

                //-- Get current version from values element
                var curVersionVal = curObj.version;

                //-- Case of isForcedVersion version
                if (curObj.isForcedVersion) {
                    maxVersionDoc = curObj;
                    throw BreakException;
                }

                //TODO MANAGE ALPHA, BETA, ... versions
                var isContinue = true;
                var curVersionValTab = curVersionVal.split(".");
                var k = 0;
                while (isContinue) {
                    k++;

                    if (curMaxVersionValTab.length < k) {
                        isContinue = false;
                        curMaxVersionValTab = curVersionValTab;
                        maxVersionDoc = curObj;
                    }

                    else if (curVersionValTab.length < k) {
                        isContinue = false;
                    }

                    else {
                        var curMaxVersionValTabElt = parseInt(curMaxVersionValTab[k - 1]);
                        var curVersionValTabElt = parseInt(curVersionValTab[k - 1]);
                        if (curVersionValTabElt > curMaxVersionValTabElt) {
                            isContinue = false;
                            curMaxVersionValTab = curVersionValTab;
                            maxVersionDoc = curObj;
                        } else if (curVersionValTabElt < curMaxVersionValTabElt) {
                            isContinue = false;
                        }
                    }
                }
            }
        );

        return  maxVersionDoc;

    } catch (e) {
        if (e === BreakException) {
            return maxVersionDoc;
        }

        throw e;
    }

}

var reduce_function_deprecated = function (key, values) {

    var maxVersionDoc = reduce_function_version(key, values);
    var idxElemToRemove = values.indexOf(maxVersionDoc);

    //- final deprecated versions array of one element
    values.splice(idxElemToRemove, 1);

    var deprecatedVersionTab = [];
    values.forEach(
        function (value) {
            deprecatedVersionTab.push(value.version);
        }
    );

    var newDoc = {
        version: deprecatedVersionTab.join(" ")
    };

    return newDoc;
}

