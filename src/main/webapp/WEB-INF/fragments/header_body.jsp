<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="header">
    <ul id="menu">
            <li>
                <b><a href="${siteUrl}"><img src="${staticContent}/images/logo_sw.png" height="17" width="16" alt="Home"/> Accueil</a> &gt;
                <a href="${baseUrl}/"> ${instance.longName}</a></b>
            </li>
            <li>
                <div>&nbsp;&nbsp;</div>
            </li>
            <li>
                <div><span>&nbsp;&nbsp;</span></div>
            </li>
            <sec:authorize ifNotGranted="ROLE_USER">
                <li>
                    <a href="javascript:authentication();"><img src="${staticContent}/images/user_suit.png" class="icon" alt="User"/> Utilisateur non authentifi&eacute;
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize ifAnyGranted="ROLE_USER">
                <li>
                   <img src="${staticContent}/images/user_suit.png" class="icon" alt="User"/> <a href="${baseUrl}/profile/${user.id}/${user.profileUrl}">${userName}</a>
                </li>
            </sec:authorize>
            <li>
                <div><span>&nbsp;&nbsp;</span></div>
            </li>
            <li><a href="${baseUrl}/help"><img src="${staticContent}/images/help.png" class="icon" alt=""/> Aide</a></li>
    </ul>
    <div id="welcome" class="ui-widget" onclick="hideWelcome();">
        <div class="ui-state-highlight ui-corner-all" style="margin-top: 25px; padding:10px">
            <p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
                <span id="welcome-text"></span></p>
        </div>
    </div>
    <div id="message" class="ui-widget" onclick="hideMessage();">
        <div class="ui-state-highlight ui-corner-all" style="margin-top: 25px; padding:10px">
            <p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
                <span id="message-text"></span></p>
        </div>
    </div>
    <div id="error" class="ui-widget" onclick="hideError();">
        <div class="ui-state-error ui-corner-all" style="margin-top: 25px; padding: 10px">
            <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
                <span id="error-text"></span></p>
        </div>
    </div>
</div>
