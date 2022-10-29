<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
	<div id="body">

		<!-- chat btn -->
		<div id="chat-circle" class="btn btn-raised">
			<div id="chat-overlay"></div>
			<i class="fa fa-solid fa-comment"></i>
		</div>

		<div id="chat-modal">

			<!-- chat list -->
			<div class="chat-box" id="chatList">
				<input type="hidden" id="memberId" value="${sessionDTO.id}"/>
				<input type="hidden" id="memberNickname" value="${sessionDTO.nickname}"/>

				<div class="chat-box-header">
					채팅
					<span class="chat-box-toggle right chat-close-btn"><i class="material-icons">close</i></span>
				</div>
				<div class="chat-box-body list">
					<div class="chat-box-overlay"></div>
					<div class="chat-logs" id="listArea">
					</div>
				</div>
			</div>

			<!-- chat room -->
			<div class="chat-box" id="chatroom">

				<div class="chat-box-header">
					<span class="chat-box-toggle left chat-list-btn"><i class="material-icons">arrow_back</i></span>
					<span id="title">title</span>
					<span class="chat-box-toggle right chat-close-btn"><i class="material-icons">close</i></span>
				</div>
				<div class="chat-box-body">
					<div class="chat-box-overlay"></div>
					<div class="chat-logs" id="msgArea">
					</div><!--chat-log -->
				</div>
				<div class="chat-input-group">
					<input type="text" class="chat-input" id="msg" placeholder="Send a message..."/>
					<button type="button" class="chat-submit" id="button-send"><i class="material-icons">send</i></button>
				</div>
			</div>

		</div>

	</div>
</div>
