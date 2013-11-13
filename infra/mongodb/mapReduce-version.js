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
             	 if (valVersionTab .length != 0) {
                    countElements++;
                    newTab.push(valVersionTab.shift());
             	 } else {
                    newTab.push("0");
                 }
             }
             var normailizedVersion = newTab.join(".");
             if (normailizedVersion > maxVersion) {
                 nbElementsMaxVersion = countElements;
                 maxVersion = normailizedVersion;
             }
        }
     );

     var maxVersionTab = maxVersion.split(".");
     while (maxVersionTab.length != nbElementsMaxVersion){
         maxVersionTab.shift();
     }

     return maxVersionTab.join(".");
}
