var zdt = JSJoda.ZonedDateTime.now().toString();

function showHowLong(){

const xhttpr = new XMLHttpRequest();

var url = 'http://localhost:8080/odds/how-long?submittedDateTime=SDT'
zdt = zdt.replace('[SYSTEM]', '');
//console.log(zdt);
url = url.replace('SDT',zdt);
xhttpr.open('GET', url, true);
 
xhttpr.send();
 
var howLongTxt;
xhttpr.onload = ()=> {
  if (xhttpr.status === 200) {
      const response = JSON.parse(xhttpr.response);
 howLongTxt = response.howLong;
 document.getElementById("text").innerHTML=howLongTxt;
   } else {
console.log("Error");
  }
};


}

setInterval(showHowLong,1000);


