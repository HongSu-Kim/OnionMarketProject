
var xmlHttp;

function getXMLHttpRequest() {

    if (window.XMLHttpRequest) {    
        return new XMLHttpRequest();
        
    } else if (window.ActiveXObject) {
    	
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
			
		} catch (e1) {
			
			try {
				return new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) {
				
			}
		}
	}

}

var httpRequest = null;

function sendRequest(url,params,callback,method){
	
	httpRequest = getXMLHttpRequest();
	
	//method 처리
	var httpMethod = method ? method : "GET";
	
	if(httpMethod!="GET" && httpMethod!="POST"){
		httpMethod = "GET";
	}
	
	//param 처리
	var httpParams = (params==null || params=="") ? null : params;
	
	//url 처리
	var httpUrl = url;
	
	if(httpMethod=="GET" && httpParams!=null){
		httpUrl = httpUrl + "?" + httpParams;
	}
	
	httpRequest.open(httpMethod,httpUrl,true);
	httpRequest.setRequestHeader(
			"Content-Type","application/x-www-form-urlencoded");
	httpRequest.onreadystatechange = callback;
	httpRequest.send(httpMethod=="POST" ? httpParams : null);
	
	
}














