var map_function_version= function (){
    var key = {
        org: this.org,
        name: this.name,
        type: this.type,
        status: this.status
    };
    emit (key , this.version)
}

var reduce_function_version = function(key, values){

    var maxVersion="";
    var nbElementsMaxVersion=0;

    values.forEach(
        function(value) {
            var countElements = 0;
            var valVersion=value;
            valVersionTab = valVersion.split(".");
            var newTab = []
            while (newTab.length!=3) {
                if (valVersionTab.length != 0) {
                    countElements++;
                    newTab.push(valVersionTab.shift());
                } else {
                    newTab.push("0");
                }
            }
            var normalizedVersion = newTab.join(".");

            if (normalizedVersion.indexOf("-")!=-1){
                maxVersion = maxVersion+".0";
            }

            if (normalizedVersion > maxVersion) {
                nbElementsMaxVersion = countElements;
                maxVersion = normalizedVersion;
            }
        }
    );

    var maxVersionTab = maxVersion.split(".");
    while (maxVersionTab.length != nbElementsMaxVersion){
        maxVersionTab.pop();
    }

    return maxVersionTab.join(".");
}

var reduce_function_deprecated = function(key, values){

    var maxVersionValue = reduce_function_version(key,values);
    var idxElemToRemove = values.indexOf(maxVersionValue);

    //- final deprecated versions array of one element
    values.splice(idxElemToRemove, 1);

    return values.join("*");
}

