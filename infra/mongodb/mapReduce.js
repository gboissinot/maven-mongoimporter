var map_function_version = function () {
    var key = {
        org: this.org,
        name: this.name,
        type: this.type,
        status: this.status
    };
    var value = {
        version: this.version,
        isReferenceVersion: this.isReferenceVersion

    };
    emit(key, value);
}

var reduce_function_version = function(key, values) {

    var maxVersionDoc;
    var curMaxVersionVal = "";

    values.forEach(
        function(curObj) {

            //-- Get current version from values element
            var curVersionVal = curObj.version;

            //-- Case of isReference version
            if (curObj.isReferenceVersion) {
                maxVersionDoc = curObj;
                return;
            }

            //-- Compute a new temp version value with threee members x.y.z
            var initialTemporaryVersionTab = curVersionVal.split(".");
            var targetTemporaryVersionTab = []
            while (targetTemporaryVersionTab.length != 3) {
                if (initialTemporaryVersionTab.length != 0) {
                    targetTemporaryVersionTab.push(initialTemporaryVersionTab.shift());
                } else {
                    targetTemporaryVersionTab.push("0");
                }
            }
            var newCurVersionWithAtLeast3Elts = targetTemporaryVersionTab.join(".");

            var curMaxVersion2Compare = curMaxVersionVal;
            //Tip: For version ending with '-', we put a '0' to previous maxVersion
            if (newCurVersionWithAtLeast3Elts.indexOf("-") != -1) {
                curMaxVersion2Compare = curMaxVersion2Compare + ".0";
            }

            //Comparison: Use String comparison
            if (newCurVersionWithAtLeast3Elts > curMaxVersion2Compare) {
                curMaxVersionVal = newCurVersionWithAtLeast3Elts;
                maxVersionDoc = curObj;
            }
        }
    );

    return maxVersionDoc;

}


var reduce_function_deprecated = function(key, values) {

    var maxVersionValue = reduce_function_version(key, values);
    var idxElemToRemove = values.indexOf(maxVersionValue);

    //- final deprecated versions array of one element
    values.splice(idxElemToRemove, 1);

    return values.join("*");
}

