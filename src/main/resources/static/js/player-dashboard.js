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

// Fermeture des modals en cliquant en dehors
document.addEventListener('click', function(event) {
    if (event.target.classList.contains('modal')) {
        event.target.classList.remove('active');
    }
}); 