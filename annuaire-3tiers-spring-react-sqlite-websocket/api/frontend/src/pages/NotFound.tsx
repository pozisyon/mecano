export default function NotFound() {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-900 text-white">
      <h1 className="text-6xl font-bold mb-4">404</h1>
      <p className="text-gray-400 mb-8">Page non trouvée 😢</p>
      <a href="/" className="px-4 py-2 bg-blue-600 rounded-lg hover:bg-blue-700">
        Retour à l’accueil
      </a>
    </div>
  );
}
