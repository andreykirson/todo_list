<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>



<head>
    <script src=https://code.jquery.com/jquery-3.1.1.min.js ></script>
    <script src="http://momentjs.com/downloads/moment.js"></script>
    <title>Title</title>

    <script>
        $(document).ready(function () {
            $.ajax({
                url: 'http://localhost:8080/todolist/GetAllCategory.do',
                type: "GET",
                dataType: "json",
                success: function(data){
                    var data = JSON.parse(JSON.stringify(data))
                    var options = $("#select-category");
                    $.each(data, function(index) {
                        options.append($("<option />").val(data[index].desc).text(data[index].desc));
                    });
                }
            });
        })
    </script>

</head>
<body>

<header>
    <h1>Todo List</h1>
    <br>
</header>

<main>
    <form name="task editor">
        <input type="text" size="100" id="input">

        <select name="select" id="select-category">
            <option value="Please select category">Please select category</option>
        </select>


        <input type="button" id="add-task" value="Add" onclick="validate()">
    </form>

    <input type="checkbox" class="viewchkbx" id="display-check" onclick="changeView(this)" >
    <label for="display-check">Display done/all</label>

    <table id="tasks-tbl">
        <thead>
        <tr>
            <td> Task description </td>
            <td> Data created </td>
            <td> Author </td>
            <td> Category </td>
            <td> Done/not </td>
        </tr>
        </thead>
        <tr></tr>
    </table>
</main>


</body>

<script>

    let lastId = 0;
    let elements = [];
    let userName;

    $(document).ready(function () {
        $.ajax({
            url: 'http://localhost:8080/todolist/getAll.do',
            type: "GET",
            dataType: "json",
            success: function(data){
                let jsonobj = JSON.parse(JSON.stringify(data));
                console.log(jsonobj);
                $.each(data, function(index) {
                        let item = {
                            id: jsonobj[index].id,
                            description: jsonobj[index].description,
                            createdTime: jsonobj[index].createdTime,
                            done: jsonobj[index].done,
                            user: jsonobj[index].user,
                            category:jsonobj[index].category
                        };

                let row =
                "<tr  id='tr-" + item.id + "'>" +
                "<td>" + "<span hidden>" + item.id + "</span>" + "<span>" + item.description + "</span>" + "</td>" +
                "<td>" + item.createdTime + "</td>" +
                "<td>" + item.user.username + "</td>" +
                "<td>" + item.category.desc + "</td>" +
                "<td>" + "<input id='ch-" + item.id + "'  onchange = handleCheckbox(this) class='checkbx' type='checkbox'>" +
                "</td>" +
                "</tr>";

                userName = item.user.username;
                $("#tasks-tbl tbody").append(row);
                elements.push(
                    {id: item.id, hidden: item.done}
                );
                let chId="ch-"+item.id;
                item.done === true ? $("#ch-"+item.id).prop("checked", true) : '';
                display();
                lastId = item.id;
                    }
                );
            }
        });
    })

    $('#add-task').click(function()  {

        let description = document.getElementById("input").value;
        let category = document.getElementById("select-category").value;
        let date = moment().format('YYYY-MM-DD H:mm:ss');
        let item = {
            id: lastId,
            description: description,
            createdTime: date,
            done: false,
            user: userName,
            desc: category
        };

        let row =
            "<tr 'tr-" + item.id + "'>" +
            "<td>" + "<span hidden>" + (++item.id) + "</span>" + "<span>" + item.description + "</span>" + "</td>" +
            "<td>" + item.createdTime + "</td>" +
            "<td>" + item.user + "</td>" +
            "<td>" + item.desc + "</td>" +
            "<td>" + "<input type=checkbox > " + "</td>" +
            "</tr>";

        elements.push(
            {id: item.id, hidden: item.done}
        );

        if (description != "") {
            $("#tasks-tbl tbody").append(row);
            document.getElementById("input").value = "";
        }
       let data = JSON.stringify (item);
        console.log(data);
        sendItem(data);
    })

    function validate() {
        let x = document.forms["task editor"]["input"].value;
        let y = document.forms["task editor"]["select-category"].value;
        if (x === "" || y === "Please select category") {
            alert("Please describe your task and select task category");
            return false;
        }
        return true;
    }


    function sendItem(item)  {
        $.ajax({
                url: 'http://localhost:8080/todolist/add.do',
                type: "POST",
                dataType: "json",
                data: item,
                contentType: "application/json",
                success: function(data) {
                    console.log(data)
            },
        })
    }

    function handleCheckbox(checkbx) {
        let id = checkbx.getAttribute("id");
        let checked = checkbx.checked;
        let data = "" + id + " " + checked;
        $.ajax({
            url: 'http://localhost:8080/todolist/update.do',
            type: "POST",
            dataType: "text",
            data: data,
            success: function(data){
                console.log(data)
            }
        });
    }

    function display() {
        for (let i = 0; i < elements.length; i++) {
                let trId = "tr-" + elements[i].id;
                document.getElementById(trId).style.display = 'table-row';
            }
    }

    function hide() {
        for (let i = 0; i < elements.length; i++) {
            if (elements[i].hidden === true) {
                let trId = "tr-" + elements[i].id;
                document.getElementById(trId).style.display = "none";
            }
        }
    }

    function changeView(viewchkbx) {
        let start = viewchkbx.checked;
        if (start === true) {
            hide();
        } else {
            display();
        }
    }
</script>

</html>
