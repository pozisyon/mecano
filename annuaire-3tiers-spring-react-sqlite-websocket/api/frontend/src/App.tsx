import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import AdminPage from "./pages/Admin";
import PublicPage from "./pages/Public";
import MainLayout from "./layouts/MainLayout";

export default function App() {
  const isAuthenticated = localStorage.getItem("authToken") !== null;

  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            <MainLayout>
              <Home />
            </MainLayout>
          }
        />
        <Route
          path="/public"
          element={
            <MainLayout>
              <PublicPage />
            </MainLayout>
          }
        />
        <Route
          path="/admin"
          element={
            isAuthenticated ? (
              <MainLayout>
                <AdminPage />
              </MainLayout>
            ) : (
              <Navigate to="/" replace />
            )
          }
        />
      </Routes>
    </BrowserRouter>
  );
}
