import { Routes, Route, Navigate } from "react-router-dom";
import AppLayout from "./layouts/AppLayout";

import Home from "./pages/Home";
import EntityListPage from "./pages/entities/EntityListPage";
import EntityCreatePage from "./pages/entities/EntityCreatePage";
import EntityEditPage from "./pages/entities/EntityEditPage";

export default function App() {
  return (
    <Routes>
      <Route element={<AppLayout />}>
        <Route path="/" element={<Home />} />

        <Route path="/entities" element={<EntityListPage />} />
        <Route path="/entities/new" element={<EntityCreatePage />} />
        <Route path="/entities/:id/edit" element={<EntityEditPage />} />

        <Route path="*" element={<Navigate to="/" replace />} />
      </Route>
    </Routes>
  );
}

