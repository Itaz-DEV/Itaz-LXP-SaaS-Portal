'use strict';

$(document).ready(function () {
    $(".mo_btn").click(function () {
        $('.mo_menu').show();
        $('.overlay').show();
    });
    $(".close_btn").click(function () {
        $('.mo_menu').hide();
        $('.overlay').hide();
    });
});
