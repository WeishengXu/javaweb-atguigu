function showDelImg(imgId) {
    const obj = document.getElementById(imgId);
    if (obj) {
        obj.style.display = 'inline';
    }
}

function hiddenDelImg(imgId) {
    const obj = document.getElementById(imgId);
    if (obj) {
        obj.style.display = 'none';
    }
}

function delReply(replyId, topicId) {
    if (confirm('是否确认删除回复？')) {
        window.location.href = 'reply.do?operate=delReply&replyId=' + replyId + '&topicId=' + topicId;
    }
}
