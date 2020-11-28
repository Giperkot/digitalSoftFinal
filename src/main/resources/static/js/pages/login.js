

document.addEventListener("DOMContentLoaded", function () {

    let authForm = document.querySelector(".auth_form");
    let registerBtn = document.querySelector(".register_btn");
    let enterBtn = document.querySelector(".enter_btn");
    // let authTypePanel = document.querySelector(".auth_type");
    // let animatedUnderscore = authTypePanel.querySelector(".animated_underscore");
    // let formSliderWrapper = document.querySelector(".form_slider_wrapper");

    /*function setUnderscorePosition (position) {
        //
        switch (position) {
            case "LEFT":
                animatedUnderscore.classList.remove("animated_underscore__right");
                animatedUnderscore.classList.add("animated_underscore__left");
                formSliderWrapper.classList.remove("form_slider_wrapper__ecp");
                break;
            case "RIGHT":
                animatedUnderscore.classList.remove("animated_underscore__left");
                animatedUnderscore.classList.add("animated_underscore__right");
                formSliderWrapper.classList.add("form_slider_wrapper__ecp");
                break;
        }
    }

    authTypePanel.addEventListener("click", function (evt) {

        let target = evt.target;

        let selection = target.closest(".auth_type__selection");

        if (!selection) {
            return;
        }

        if (selection.classList.contains("login_passw_btn")) {
            setUnderscorePosition("LEFT");
        }

        if (selection.classList.contains("ecp_btn")) {
            setUnderscorePosition("RIGHT");
        }

    });*/

    enterBtn.addEventListener("click", function (evt) {
        // Кнопка войти

        evt.preventDefault();
        evt.stopPropagation();

        let emailField = authForm.querySelector(".user_field");
        let passwordField = authForm.querySelector(".password_field");


        // console.log("click");
        helper.getHttpPromise({
            method: "POST",
            url: "/login?username=" + emailField.value + "&password=" + passwordField.value,
            contentType: "application/json",
            /*jsonData: {
                username: emailField.value,
                password: passwordField.value
            }*/
        }).then(function (responseObj) {
            if (responseObj.xhr.responseURL.indexOf("login") === -1) {
                // Успех
                window.location = "/";
            }

            let loginMessage = authForm.querySelector(".login_message");

            loginMessage.innerHTML = "Логин или пароль введены не верно.";
            loginMessage.classList.add("red");
        });


    });


    registerBtn.addEventListener("click", function (evt) {
        // Кнопка войти
        evt.preventDefault();
        evt.stopPropagation();

        let loginMessage = authForm.querySelector(".login_message");

        loginMessage.innerHTML = "Заявка на регистрацию на модерации.";
        loginMessage.classList.add("green");
        formToggle(evt);
    });


    var panelOne = $('.form-panel.two').height(),
        panelTwo = $('.form-panel.two')[0].scrollHeight;

    let formToggle = function(e) {
        e.preventDefault();
        $(this).removeClass('visible');
        $('.form-panel.one').removeClass('hidden');
        $('.form-panel.two').removeClass('active');
        $('.form').animate({
            'height': panelOne
        }, 200);
    }

    $('.form-panel.two').not('.form-panel.two.active').on('click', function(e) {
        e.preventDefault();

        $('.form-toggle').addClass('visible');
        $('.form-panel.one').addClass('hidden');
        $('.form-panel.two').addClass('active');
        $('.form').animate({
            'height': panelTwo
        }, 200);
    });

    $('.form-toggle').on('click', formToggle);


});
