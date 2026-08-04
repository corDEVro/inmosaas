import React, { useState } from 'react';
import { useAuth } from './context/AuthContext';

export function App() {
  const { user, login, logout, isAuthenticated } = useAuth();
  
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setErrorMsg('');
    setLoading(true);

    try {
      await login({ email, password });
    } catch (err: any) {
      console.error(err);
      setErrorMsg(err.response?.data?.message || 'Error al iniciar sesión o conectar con el servidor');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-slate-900 text-white flex flex-col items-center justify-center p-4">
      <div className="w-full max-w-md bg-slate-800 p-8 rounded-xl shadow-2xl border border-slate-700">
        <h1 className="text-2xl font-bold mb-6 text-center text-blue-400">
          🧪 Prueba de Conexión Backend
        </h1>

        {isAuthenticated ? (
          <div className="space-y-4 text-center">
            <div className="p-4 bg-emerald-950 border border-emerald-500/30 rounded-lg">
              <p className="text-emerald-400 font-semibold">¡Conexión Exitosa! 🎉</p>
              <p className="text-sm mt-1 text-slate-300">Usuario logueado: <strong className="text-white">{user?.email}</strong></p>
            </div>
            <button
              onClick={logout}
              className="w-full py-2 bg-red-600 hover:bg-red-500 rounded-lg font-medium transition"
            >
              Cerrar Sesión (Logout)
            </button>
          </div>
        ) : (
          <form onSubmit={handleSubmit} className="space-y-4">
            {errorMsg && (
              <div className="p-3 bg-red-950 border border-red-500/30 text-red-400 text-sm rounded-lg">
                {errorMsg}
              </div>
            )}
            
            <div>
              <label className="block text-sm font-medium text-slate-300 mb-1">Email</label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                className="w-full px-3 py-2 bg-slate-900 border border-slate-700 rounded-lg text-white focus:outline-none focus:border-blue-500"
                placeholder="usuario@ejemplo.com"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-slate-300 mb-1">Contraseña</label>
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                className="w-full px-3 py-2 bg-slate-900 border border-slate-700 rounded-lg text-white focus:outline-none focus:border-blue-500"
                placeholder="••••••••"
              />
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full py-2 bg-blue-600 hover:bg-blue-500 disabled:opacity-50 rounded-lg font-medium transition"
            >
              {loading ? 'Conectando...' : 'Probar Login'}
            </button>
          </form>
        )}
      </div>
    </div>
  );
}

export default App;