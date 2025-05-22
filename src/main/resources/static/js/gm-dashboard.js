// Gestion des onglets
document.addEventListener('DOMContentLoaded', function() {
    const tabButtons = document.querySelectorAll('.tab-btn');
    const tabPanes = document.querySelectorAll('.tab-pane');

    tabButtons.forEach(button => {
        button.addEventListener('click', () => {
            // Désactiver tous les onglets
            tabButtons.forEach(btn => btn.classList.remove('active'));
            tabPanes.forEach(pane => pane.classList.remove('active'));

            // Activer l'onglet sélectionné
            button.classList.add('active');
            const tabId = button.getAttribute('data-tab');
            document.getElementById(tabId).classList.add('active');
        });
    });
});

// Gestion des modals
function showModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.classList.add('active');
}

function hideModal(modalId) {
    const modal = document.getElementById(modalId);
    modal.classList.remove('active');
}

// Gestion des parties
function showCreateGameModal() {
    showModal('createGameModal');
}

function editGame(button) {
    const gameId = button.getAttribute('data-game-id');
    // TODO: Implémenter la logique d'édition
    console.log('Édition de la partie:', gameId);
}

function deleteGame(button) {
    const gameId = button.getAttribute('data-game-id');
    if (confirm('Êtes-vous sûr de vouloir supprimer cette partie ?')) {
        // TODO: Implémenter la logique de suppression
        console.log('Suppression de la partie:', gameId);
    }
}

// Gestion des entités
function showCreateEntityModal() {
    showModal('createEntityModal');
}

function editEntity(button) {
    const entityId = button.getAttribute('data-entity-id');
    // TODO: Implémenter la logique d'édition
    console.log('Édition de l\'entité:', entityId);
}

function deleteEntity(button) {
    const entityId = button.getAttribute('data-entity-id');
    if (confirm('Êtes-vous sûr de vouloir supprimer cette entité ?')) {
        // TODO: Implémenter la logique de suppression
        console.log('Suppression de l\'entité:', entityId);
    }
}

// Fermeture des modals en cliquant en dehors
document.addEventListener('click', function(event) {
    if (event.target.classList.contains('modal')) {
        event.target.classList.remove('active');
    }
}); 