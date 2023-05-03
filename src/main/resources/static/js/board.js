let index = {
    init: function() {
        $("#btn-board-save").on("click", () => {
            this.save()
        })
        // $("#btn-board-update").on("click", () => {
        //     this.update()
        // })
        // $("#btn-board-delete").on("click", () => {
        //     this.deleteById()
        // })
    },

    save: function (){
        // alert("board save 함수 호출됨");
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),

            token: $("meta[name='_csrf']").attr("content"),
            header: $("meta[name='_csrf_header']").attr("content")
        }
        // console.log(data);

        $.ajax({
            type:"POST",
            url: "/api/board",
            data: JSON.stringify(data),  //http body data
            contentType: "application/json; charset=utf-8",  // what kind of MIME
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(data.header, data.token);
            }
        }).done(function(resp){
            alert("글쓰기가 완료되었습니다.")
            location.href = "/"
        }).fail(function(error){
            alert(JSON.stringify(error))
        })
    },

    // update: function (){
    //     let data = {
    //         id: $("#id").val(),
    //         title: $("#title").val(),
    //         content: $("#content").val(),
    //
    //         token: $("meta[name='_csrf']").attr("content"),
    //         header: $("meta[name='_csrf_header']").attr("content")
    //     }
    //
    //     $.ajax({
    //         type:"PUT",
    //         url: "/api/board/" + data.id,
    //         data: JSON.stringify(data),  //http body data
    //         contentType: "application/json; charset=utf-8",  // what kind of MIME
    //         dataType: "json",
    //         beforeSend: function(xhr) {
    //             xhr.setRequestHeader(data.header, data.token);
    //         }
    //     }).done(function(resp){
    //         alert("수정이 완료되었습니다.")
    //         location.href = "/"
    //     }).fail(function(error){
    //         alert(JSON.stringify(error))
    //     })
    // },
    //
    // deleteById: function (){
    //     let data = {
    //         id: $("#id").text(),
    //         token: $("meta[name='_csrf']").attr("content"),
    //         header: $("meta[name='_csrf_header']").attr("content")
    //     }
    //
    //     $.ajax({
    //         type:"DELETE",
    //         url: "/api/board/" + data.id,
    //         data: JSON.stringify(data),  //http body data
    //         contentType: "application/json; charset=utf-8",  // what kind of MIME
    //         dataType: "json",
    //         beforeSend: function(xhr) {
    //             xhr.setRequestHeader(data.header, data.token);
    //         }
    //     }).done(function(resp){
    //         alert("글삭제가 완료되었습니다.")
    //         location.href = "/"
    //     }).fail(function(error){
    //         alert(JSON.stringify(error))
    //     })
    // }
}

index.init()