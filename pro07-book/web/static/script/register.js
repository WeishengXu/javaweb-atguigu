function checkForm(KAPTCHA_SESSION_KEY) {
    const userName = document.getElementById("userName").value;
    const userNameSpan = document.getElementById("userNameSpan");
    const userPwd = document.getElementById("userPwd").value;
    const userPwdSpan = document.getElementById("userPwdSpan");
    const userPwd2 = document.getElementById("userPwd2").value;
    const userPwd2Span = document.getElementById("userPwd2Span");
    const userEmail = document.getElementById("userEmail").value;
    const userEmailSpan = document.getElementById("userEmailSpan");
    const code = document.getElementById("code").value;
    const codeSpan = document.getElementById("codeSpan");

    const userNameReg = /[0-9a-zA-Z]{6,16}/;
    if (!userNameReg.test(userName)) {
        userNameSpan.style.visibility = "visible";
        return false;
    } else {
        userNameSpan.style.visibility = "hidden";
    }

    const userPwdReg = /[\w]{8,}/;
    if (!userPwdReg.test(userPwd)) {
        userPwdSpan.style.visibility = "visible";
        return false;
    } else {
        userPwdSpan.style.visibility = "hidden";
    }

    if (userPwd2 != userPwd) {
        userPwd2Span.style.visibility = "visible";
        return false;
    } else {
        userPwd2Span.style.visibility = "hidden";
    }

    const userEmailReg = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,11}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,11}[a-zA-Z0-9])?)*$/;
    if (!userEmailReg.test(userEmail)) {
        userEmailSpan.style.visibility = "visible";
        return false;
    } else {
        userEmailSpan.style.visibility = "hidden";
    }

    if (code != KAPTCHA_SESSION_KEY) {
        codeSpan.style.visibility = "visible";
        return false;
    } else {
        codeSpan.style.visibility = "hidden";
    }
    return true;
}

let xmlHttpRequest;

function createXMLHttpRequest() {
    if (window.XMLHttpRequest) {
        xmlHttpRequest = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        try {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {
            xmlHttpRequest = new ActiveXObject("Msxml.XMLHTTP");
        }
    }
}

function checkUserName(userName) {
    createXMLHttpRequest();
    const url = "user.do?operate=checkUserName&userName=" + userName;
    xmlHttpRequest.open("GET", url, true);
    xmlHttpRequest.onreadystatechange = checkUserNameCB;
    xmlHttpRequest.send();
}

function checkUserNameCB() {
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        const responseText = xmlHttpRequest.responseText;
        if (responseText == "{'userName': '1'}") {
            alert("用户已经被注册！" + responseText);
        } else {
            alert("用户可以注册！" + responseText);
        }
    }
}
