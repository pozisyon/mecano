import { Outlet } from "react-router-dom";
import Header from "../components/Header";
import NavBar from "../components/NavBar";
import Footer from "../components/Footer";

export default function AppLayout() {
  return (
    <div className="app">
      <Header />
      <NavBar />
      <main className="container">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}
