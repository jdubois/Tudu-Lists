if (!document.getElementById) {
    document.getElementById = function() { return null; }
}

function initMenu() {
    var uls = document.getElementsByTagName("ul");
    for (i = 0; i < uls.length; i++) {
        if (uls[i].className == "menuList") {
            decorateMenu(uls[i]);
        }
    }
}

function decorateMenu(menu) {
    var items = menu.getElementsByTagName("li");
    for (var i=0; i < items.length; i++) {
        items[i].firstChild.myIndex = i;
        // retain any existing onclick handlers from menu-config.xml
        if (items[i].firstChild.onclick) {
            items[i].firstChild.onclick=function() { 
                eval(items[this.myIndex].firstChild.getAttribute("onclick"));
                setCookie("menuSelected", this.myIndex); 
                };
        } else {
            items[i].firstChild.onclick=function() { 
                setCookie("menuSelected", this.myIndex); 
            };
        }
    }
    activateMenu(items);
}

function activateMenu(items) {
    var activeMenu;
    var found = 0;
    for (var i=0; i < items.length; i++) {
        var url = items[i].firstChild.getAttribute("href");
        var current = document.location.toString();
        if (current.indexOf(url) != -1) {
            found++;
        }
    }
     
    // more than one found, use cookies
    if (found > 1) {  
        var menuSelected = getCookie("menuSelected"); 
        if (items[menuSelected].parentNode.className == "submenu") {
            items[menuSelected].firstChild.className="selected";
            items[menuSelected].parentNode.parentNode.className="selected";
        } else {            
            items[menuSelected].className+="selected";
        }
    } else {
        // only one found, match on URL
        for (var i=0; i < items.length; i++) {
            var url = items[i].firstChild.getAttribute("href");
            var current = document.location.toString();
            if (current.indexOf(url) != -1) {
                if (items[i].parentNode.className == "submenu") {
                    items[i].firstChild.className="selected";
                    items[i].parentNode.parentNode.className="selected";
                } else {            
                    items[i].className+="selected";
                }
            }
        }
    }
}

// Select the menu that matches the URL when the page loads
window.onload=initMenu;

// =========================================================================
//                          Cookie functions 
// =========================================================================
/* This function is used to set cookies */
function setCookie(name,value,expires,path,domain,secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

/* This function is used to get cookies */
function getCookie(name) {
	var prefix = name + "=" 
	var start = document.cookie.indexOf(prefix) 

	if (start==-1) {
		return null;
	}
	
	var end = document.cookie.indexOf(";", start+prefix.length) 
	if (end==-1) {
		end=document.cookie.length;
	}

	var value=document.cookie.substring(start+prefix.length, end) 
	return unescape(value);
}

/* This function is used to delete cookies */
function deleteCookie(name,path,domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}