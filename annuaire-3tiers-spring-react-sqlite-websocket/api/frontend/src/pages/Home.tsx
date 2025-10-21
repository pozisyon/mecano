import { motion } from "framer-motion";
import { Users, Search, Shield, Database } from "lucide-react";
import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="min-h-screen flex flex-col bg-gradient-to-b from-gray-900 via-gray-800 to-gray-900 text-white">
      {/* HEADER */}
      <header className="flex items-center justify-between px-8 py-5 border-b border-gray-700 bg-gray-800/40 backdrop-blur-md sticky top-0 z-50">
        <h1 className="text-2xl font-bold tracking-wide">
          Annuaire<span className="text-blue-400">+</span>
        </h1>
        <nav className="flex space-x-6 text-sm">
          <Link to="/" className="hover:text-blue-400 transition">
            Accueil
          </Link>
          <Link to="/members" className="hover:text-blue-400 transition">
            Membres
          </Link>
          <Link to="/admin" className="hover:text-blue-400 transition">
            Admin
          </Link>
        </nav>
      </header>

      {/* HERO */}
      <main className="flex-1 flex flex-col items-center justify-center text-center px-6 py-16">
        <motion.h2
          className="text-5xl md:text-6xl font-extrabold mb-6 leading-tight"
          initial={{ opacity: 0, y: -40 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
        >
          Bienvenue sur <span className="text-blue-400">Annuaire+</span>
        </motion.h2>

        <motion.p
          className="text-gray-300 max-w-2xl mb-10 text-lg"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.4, duration: 0.8 }}
        >
          Gérez facilement vos membres, accédez à l’espace administrateur, et
          explorez une interface intuitive pour votre application 3-tiers.
        </motion.p>

        <motion.div
          initial={{ opacity: 0, scale: 0.8 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ delay: 0.8 }}
        >
          <Link
            to="/admin"
            className="bg-blue-500 hover:bg-blue-600 text-white font-semibold px-8 py-3 rounded-xl shadow-lg transition transform hover:scale-105"
          >
            Espace Administrateur
          </Link>
        </motion.div>
      </main>

       {/* FEATURE SECTION */}
            <section className="grid grid-cols-1 md:grid-cols-3 gap-8 px-8 pb-20">
              {[
                {
                  icon: <Users className="w-10 h-10 text-blue-400" />,
                  title: "Gestion des Membres",
                  desc: "Ajoutez, modifiez et consultez facilement vos membres via l’API Spring Boot.",
                },
                {
                  icon: <Search className="w-10 h-10 text-blue-400" />,
                  title: "Recherche Instantanée",
                  desc: "Accédez rapidement à n’importe quel membre grâce à une interface réactive.",
                },
                {
                  icon: <Database className="w-10 h-10 text-blue-400" />,
                  title: "Base Sécurisée",
                  desc: "Les données sont stockées localement dans SQLite, avec intégration Hibernate.",
                },
                {
                  icon: <Shield className="w-10 h-10 text-blue-400" />,
                  title: "Sécurité intégrée",
                  desc: "Spring Security protège vos endpoints et gère l’accès à l’espace admin.",
                },
              ].map((feature, i) => (
                <motion.div
                  key={i}
                  className="bg-gray-800 p-8 rounded-2xl shadow-md hover:shadow-blue-500/30 transition"
                  initial={{ opacity: 0, y: 30 }}
                  animate={{ opacity: 1, y: 0 }}
                  transition={{ delay: 0.2 * i }}
                >
                  <div className="flex justify-center mb-4">{feature.icon}</div>
                  <h3 className="text-xl font-semibold mb-2">{feature.title}</h3>
                  <p className="text-gray-400 text-sm">{feature.desc}</p>
                </motion.div>
              ))}
            </section>

            {/* FOOTER */}
            <footer className="text-center py-6 border-t border-gray-700 text-gray-500 text-sm">
              © {new Date().getFullYear()} Annuaire+. Tous droits réservés.
            </footer>
          </div>
        );
      }
