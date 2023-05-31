function delTopic(topicId) {
    if (confirm('是否确认删除回复？')) {
        window.location.href = "topic.do?operate=delTopic&topicId=" + topicId;
    }
}

function checkNewTopicDisplay(divId) {
    const obj = document.getElementById(divId);
    if (obj) {
        if (obj.style.display == 'inline') {
            obj.style.display = 'none'
        } else {
            obj.style.display = 'inline'
        }
    }
}
