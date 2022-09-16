let cnt = document.getElementsByName("itemCount")[0].value;

function checkLogin(consumerId) {
    if(consumerId =="" || consumerId == null) {
        alert("로그인을 해주세요.");
        return false;
    }
    return true;
}

function addItemToCart(consumerId, id) {
    if(checkLogin(consumerId)) {
        let url = "/item/" + id + "/cart";

        console.log(cnt);
        console.log(id);

        if (cnt <= 0) {
            alert("수량을 선택해 주세요");
            return ;
        }
        fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "id": id,
                "cnt": cnt,
            })
        })
            .then((response) => response.json())
            .then(
                alert("장바구니에 담겼습니다.")
            );
    }
}

function goOrder(id) {
    if (cnt <= 0) {
        alert("수량을 선택해 주세요");
        return;
    }

    let url = "/consumer/item/" + id + "/purchase";
    fetch(url)
        .then((response) => response.json())
        .then(alert("주문 페이지로 이동합니다."));
}

let submitBtn = document.getElementById("submit_button");
submitBtn.addEventListener("click", function() {
    var submitForm = document.submitForm;
    var photo = submitForm.photo.value;
    if(photo=="") {
        alert("사진이 비었습니다.");
        return false;
    }
    submitForm.action = "/community/write";
    submitForm.method = "POST";
    submitForm.submit();
})