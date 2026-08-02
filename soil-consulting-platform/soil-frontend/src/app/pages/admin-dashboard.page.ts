import { Component } from '@angular/core';

@Component({
  standalone: true,
  template: `
  <section class="dashboard">
    <h1>Dashboard admin</h1>
    <div class="grid">
      <article><h3>Demandes clients</h3><p>Analyser, affecter un statut, envoyer un devis.</p></article>
      <article><h3>Services</h3><p>Créer et modifier les services proposés.</p></article>
      <article><h3>Paiements</h3><p>Suivre les paiements en attente, payés ou échoués.</p></article>
      <article><h3>Fichiers</h3><p>Consulter les documents soumis par les clients.</p></article>
    </div>
  </section>`
})
export class AdminDashboardPage {}
