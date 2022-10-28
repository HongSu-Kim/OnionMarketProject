<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="spad">

	<div class="container">
		<div id="body">

			<!-- chat btn -->
			<div id="chat-circle" class="btn btn-raised">
				<div id="chat-overlay"></div>
				<i class="fa fa-solid fa-comment"></i>
			</div>

			<!-- chat box -->
			<div class="chat-box">

				<input type="hidden" id="chatroomId" value="${chatroomDTO.chatroomId}"/>
				<input type="hidden" id="memberId" value="${member.id}"/>
				<input type="hidden" id="memberNickname" value="${member.nickname}"/>
				<%--<c:set var="seller" value="${chatroomDTO.memberDTO}"/>
				<c:set var="buyer" value="${chatroomDTO.productDTO.memberDTO}"/>
				<c:set var="user" value="${seller.id == member.id ? buyer.nickname : seller.nickname}"/>--%>

				<div class="chat-box-header">
					ChatBot
					<span class="chat-box-toggle"><i class="material-icons">close</i></span>
				</div>
				<div class="chat-box-body">
					<div class="chat-box-overlay"></div>
					<div class="chat-logs" id="msgArea">
						<c:forEach var="chatDTO" items="${chatroomDTO.chatDTOSlice.content}">
							<c:if test="${chatDTO.memberId == member.id}">
								<div id="cm-msg" class="chat-msg self">
									<div class="cm-msg-text">${chatDTO.message}</div>
								</div>
							</c:if>
							<c:if test="${chatDTO.memberId != member.id}">
								<div id="cm-msg" class="chat-msg user">
									<div class="cm-msg-text">${chatDTO.message}</div>
								</div>
							</c:if>
						</c:forEach>
						<div id="padding" class="chat-msg padding"></div>
					</div><!--chat-log -->
				</div>
				<div class="chat-input-group">
					<input type="text" class="chat-input" id="msg" placeholder="Send a message..."/>
					<button type="button" class="chat-submit" id="button-send"><i class="material-icons">send</i></button>
				</div>
			</div>

		</div>
	</div>

</section>