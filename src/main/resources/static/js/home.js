function applyFilter(){
    const categorySelect = document.querySelector("#categorySelect");
    const searchBar = document.querySelector("#searchBar");

    if(!categorySelect || !searchBar) return;

    if(!categorySelect.value && !searchBar.value){
        resetFilter();
        return;
    }
    const encheres = document.querySelectorAll(".enchere")

    encheres.forEach(enchere => {
        const enchereTitre = enchere.querySelector(".enchere-titre").textContent

        if(!enchereTitre) return;

        let resultFilterSelect = true;
        let resultFilterSearchBar = true;

        if(categorySelect.value && !enchere.classList.contains(categorySelect.value)) {
            resultFilterSelect = false;
        }
        else if (searchBar.value && !enchereTitre.includes(searchBar.value)){
            resultFilterSearchBar = false;
        }
        resultFilterSelect && resultFilterSearchBar ? enchere.classList.remove("hidden") : enchere.classList.add("hidden")
    })
}

function resetFilter(){
    const categorySelect = document.querySelector("#categorySelect");
    const searchBar = document.querySelector("#searchBar");

    if(!categorySelect || !searchBar) return;

    searchBar.value = "";
    categorySelect.value = "";

    const encheres = document.querySelectorAll(".enchere")
    encheres.forEach(enchere => {
            enchere.classList.remove("hidden")
    })

}

function toogleTypeChoise(isAchatSelected){
    // select achat
    const selectAchat = document.querySelector("#achatSelect");

    // select vente
    const selectVente = document.querySelector("#venteSelect");

    if(!selectAchat || !selectVente){
        console.log("Les selecteurs achats et vente ne sont pas trouvable");
        return;
    }

    // si on sélectionne achat
    if(isAchatSelected){
        selectAchat.disabled = false;
        selectVente.disabled = true;
    }
    // si c'est vente
    else {
        selectVente.disabled = false;
        selectAchat.disabled = true;
    }

}