import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  standalone: true,
  imports: [RouterLink],
  template: `
  <main class="hero">
    <section class="hero-text">
      <p class="eyebrow">Sciences du sol • Recherche • Analyse statistique</p>
      <h1>Expertise scientifique pour vos mémoires, thèses, articles et données agricoles.</h1>
      <p>Correction académique, appui méthodologique, analyse des sols, statistiques, graphiques et tableaux scientifiques prêts pour publication.</p>
      <div class="actions">
        <a routerLink="/dashboard" class="btn primary">Demander un service</a>
        <a routerLink="/admin" class="btn secondary">Voir admin</a>
      </div>
    </section>
    <section class="photo-card">
      <div class="photo-placeholder">Photo du professeur</div>
      <h2>Doctorant en sciences du sol</h2>
      <p>Microbiologie et biochimie du sol — UFLA, Brésil</p>
    </section>
  </main>
  <section class="services">
    <article><h3>Révision académique</h3><p>Mémoires, thèses, articles, cohérence scientifique et qualité rédactionnelle.</p></article>
    <article><h3>Analyse des sols</h3><p>Interprétation physique, chimique et biologique des sols agricoles.</p></article>
    <article><h3>Statistiques & figures</h3><p>ANOVA, corrélations, régressions, modèles mixtes, graphiques et tableaux.</p></article>
  </section>
  <footer>© 2026 Soil Consulting — Mentions légales — LinkedIn — ResearchGate — Email</footer>
  `
})
export class LandingPage {}
