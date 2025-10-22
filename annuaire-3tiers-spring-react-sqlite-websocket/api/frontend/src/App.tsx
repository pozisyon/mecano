import { BrowserRouter, Routes, Route } from "react-router-dom";

import Home from "./pages/Home";
import AdminPage from "./pages/Admin";
import PublicPage from "./pages/Public";
import Members from "./pages/Members";
import NotFound from "./pages/NotFound";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Page d'accueil */}
        <Route path="/" element={<Home />} />

        {/* Pages internes */}

        <Route path="/members" element={<Members />} />
                <Route path="/public" element={<PublicPage />} />
                <Route path="/admin" element={<AdminPage />} />

        {/* Gestion des routes inconnues */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
