import { Component } from '@angular/core';

@Component({
  standalone: true,
  template: `
  <section class="dashboard">
    <h1>Dashboard utilisateur</h1>
    <div class="grid">
      <article><h3>Nouvelle demande</h3><p>Soumettre mémoire, thèse, article, données Excel/CSV ou rapport.</p></article>
      <article><h3>Mes demandes</h3><p>3 demandes en cours — statut, échéance et documents.</p></article>
      <article><h3>Paiements</h3><p>Factures, devis et paiements sécurisés.</p></article>
      <article><h3>Notifications</h3><p>Suivi des messages et changements de statut.</p></article>
    </div>
  </section>`
})
export class UserDashboardPage {}
