/* 
 * By ASz
 */

window.onerror=new Function("return true");

function fullscr(){
if(document.documentElement.requestFullscreen){document.documentElement.requestFullscreen()}
if(document.documentElement.msRequestFullscreen){document.documentElement.msRequestFullscreen()}
if(document.documentElement.mozRequestFullScreen){document.documentElement.mozRequestFullScreen()}
if(document.documentElement.webkitRequestFullScreen){document.documentElement.webkitRequestFullScreen()}
if(typeof window.ActiveXObject !== "undefined"){
var wscript = new ActiveXObject("WScript.Shell");
if(wscript !== null){wscript.SendKeys("{F11}")}
}
}

function fullcls(){
if(document.exitFullscreen){document.exitFullscreen()}
if(document.msExitFullscreen){document.msExitFullscreen()}
if(document.mozCancelFullScreen){document.mozCancelFullScreen()}
if(document.webkitCancelFullScreen){document.webkitCancelFullScreen()}
if(typeof window.ActiveXObject !== "undefined"){
var wscript = new ActiveXObject("WScript.Shell");
if(wscript !== null) {wscript.SendKeys("{F11}")}
}
}

document.onkeydown=function(e){
e = e ? e : window.event;
if(e.keyCode==13){fullscr();return false;}
if(e.keyCode==27){fullcls();return false;}
}