document.addEventListener("DOMContentLoaded", function () {
    console.log("Init")
    const passwordInput = document.getElementById("password");
    const passwordConfInput = document.getElementById("password_conf");
    const form = document.querySelector("form");
    const errorMessage = document.createElement("p");

    errorMessage.classList.add("text-red-500", "text-sm", "mt-1");
    passwordConfInput.insertAdjacentElement("afterend", errorMessage);


    function validatePasswordConfirmation() {
        if (passwordInput.value !== passwordConfInput.value) {
            errorMessage.textContent = "Les mots de passe ne correspondents pas.";
            return false;
        }
        errorMessage.textContent = "";
        return true;
    }

    passwordInput.addEventListener("input", validatePasswordConfirmation);
    passwordConfInput.addEventListener("input", validatePasswordConfirmation);

    form.addEventListener("submit", function (event) {
        if (!validatePasswordConfirmation()) {
            event.preventDefault();
        }
    })
})