(function (window, $, console) {
    "use strict";

    var selectorsCall,
        domManipulationCall,
        clickHandlersCall,
        ajaxCall,
        filterCall;

    var addToList,
        getListOfPosts,
        buttonClickHandler,
        deleteHandler,
        addSinglePost,
        deleteItem,
        userFilterChanged;

    addToList = function (post) {
        var $oldList = $('ul#posts'),
            $newItem = $('<li/>').appendTo($oldList),
            $deleteButton = $('<span/>');

        $newItem.text(post.title);
        $newItem.attr('data-id', post.id);

        $deleteButton.appendTo($newItem);
        $deleteButton.text('X');
        $deleteButton.css('color', 'red');
        $deleteButton.click(deleteHandler.bind(undefined, post.id));
    };

    getListOfPosts = function (user) {
        var url = 'http://jsonplaceholder.typicode.com/posts',
            limit = Infinity;

        if (user) {
            url += '?userId=' + user;
        } else {
            limit = 5;
        }

        $.get(url, function (data) {
            var i,
                len;

            // old jQuery
            data = JSON.parse(data);
            len = data.length;

            $('#posts').empty();

            for (i = 0; i < len && i < limit; i += 1) {
                addToList(data[i]);
            }
        }, 'json');
    };

    buttonClickHandler = function () {
        var $oldInput = $('#textinput'),
            value = $oldInput.val();

        if (!value) {
            window.alert("you must enter text");
            return;
        }

        $.post('http://jsonplaceholder.typicode.com/posts', {
            userId: 1,
            title: value,
            body: ''
        }, function (result) {
            // old jQuery
            result = JSON.parse(result);
            addSinglePost(result.id);
        });
    };

    addSinglePost = function (id) {
        $.get('http://jsonplaceholder.typicode.com/posts/' + id, function (data) {
            // old jQuery
            data = JSON.parse(data);
            addToList(data);

        }, 'json');
    };

    deleteHandler = function (id) {
        window.alert('deleting');

        var accepted = window.confirm("Are you sure you want to delete the item?");
        if (!accepted) {
            return;
        }

        $.ajax({
            url: 'http://jsonplaceholder.typicode.com/posts/' + id,
            type: 'DELETE',
            success: deleteItem.bind(undefined, id)
        });
    };

    deleteItem = function (id) {
        var $itemToDelete = $('ul#posts li[data-id=' + id + ']');
        $itemToDelete.remove();
    };

    userFilterChanged = function () {
        getListOfPosts(this.value);
    };

    selectorsCall = function () {
        var $firstAnchorInFooter = $('#footer a');
        console.log($firstAnchorInFooter.attr('title'));

        var $firstColumnText = $('#col1 p');
        console.log($firstColumnText.text());
    };

    domManipulationCall = function () {
        var $list = $('ul#menu-top-level-menu'),
            $newItem = $('<li/>');

        $('<a/>').text("New button").appendTo($newItem);
        $newItem.appendTo($list);

        var $newDiv = $('<div/>').attr('id', 'dynamiccontent');
        $newDiv.prependTo($('#footer'));

        $('<input/>').attr('id', 'textinput').appendTo($newDiv);
        $('<button/>').attr('id', 'addbutton').appendTo($newDiv).text("New Item");
        $('<ul/>').attr('id', 'posts').appendTo($newDiv);
    };

    clickHandlersCall = function () {
        var $oldMenuItem = $('ul#menu-top-level-menu li:last a');

        $oldMenuItem.click(function () {
            window.alert("Hello World!");
        });

        $oldMenuItem.click(function () {
            var $firstColumn = $('#home .inscreen div::nth-child(1)'),
                $secondColumn = $('#home .inscreen div::nth-child(2)');

            $secondColumn.insertBefore($firstColumn);
        });
    };

    ajaxCall = function () {
        var $oldButton = $('#addbutton');

        getListOfPosts();

        $oldButton.click(buttonClickHandler);
    };

    filterCall = function () {
        var $userFilterInput = $('<input/>');

        $userFilterInput.insertBefore($('#posts'));

        $userFilterInput.change(userFilterChanged);
    };

    selectorsCall();
    domManipulationCall();
    clickHandlersCall();
    ajaxCall();
    filterCall();

}(this, this.jQuery, this.console));
