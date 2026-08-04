import { useState } from 'react';
import { 
  Building2, 
  Users, 
  Key, 
  TrendingUp, 
  Bell, 
  Search, 
  Plus, 
  ChevronRight,
  LogOut,
  Home,
  FileText
} from 'lucide-react';

export function AppPrueba() {
  const [activeTab, setActiveTab] = useState('dashboard');

  return (
    <div className="flex h-screen bg-[#E0E6ED] font-sans antialiased text-[#2A3B4C]">
      {/* ----------------- SIDEBAR ----------------- */}
      <aside className="w-64 bg-[#2A3B4C] text-white flex flex-col justify-between shadow-xl">
        <div>
          {/* Logo Cordevro Header Sidebar */}
          <div className="p-6 border-b border-[#2A3B4C]/40 flex items-center gap-3">
            {/* SVG del Isotipo Hexagonal Cordevro */}
            <div className="w-10 h-10 rounded-lg bg-[#A8DADC]/15 flex items-center justify-center text-[#A8DADC]">
              <svg className="w-7 h-7" viewBox="0 0 100 100" fill="none" stroke="currentColor" strokeWidth="8" strokeLinecap="round" strokeLinejoin="round">
                <polygon points="50,5 90,27.5 90,72.5 50,95 10,72.5 10,27.5" />
                <polyline points="38,40 25,50 38,60" />
                <polyline points="62,40 75,50 62,60" />
                <line x1="53" y1="36" x2="47" y2="64" />
              </svg>
            </div>
            <div>
              <h1 className="font-bold text-lg tracking-tight leading-tight text-white">
                cordevro
              </h1>
              <p className="text-[10px] tracking-widest text-[#A8DADC] font-semibold uppercase">
                InmoSaaS
              </p>
            </div>
          </div>

          {/* Menú de Navegación */}
          <nav className="p-4 space-y-1">
            <button 
              onClick={() => setActiveTab('dashboard')}
              className={`w-full flex items-center gap-3 px-4 py-3 rounded-lg text-sm font-medium transition ${
                activeTab === 'dashboard' 
                  ? 'bg-[#A8DADC] text-[#2A3B4C] font-semibold shadow-md' 
                  : 'text-slate-300 hover:bg-[#A8DADC]/10 hover:text-white'
              }`}
            >
              <Home className="w-5 h-5" />
              Dashboard
            </button>
            <button 
              onClick={() => setActiveTab('inmuebles')}
              className={`w-full flex items-center gap-3 px-4 py-3 rounded-lg text-sm font-medium transition ${
                activeTab === 'inmuebles' 
                  ? 'bg-[#A8DADC] text-[#2A3B4C] font-semibold shadow-md' 
                  : 'text-slate-300 hover:bg-[#A8DADC]/10 hover:text-white'
              }`}
            >
              <Building2 className="w-5 h-5" />
              Inmuebles
            </button>
            <button 
              onClick={() => setActiveTab('clientes')}
              className={`w-full flex items-center gap-3 px-4 py-3 rounded-lg text-sm font-medium transition ${
                activeTab === 'clientes' 
                  ? 'bg-[#A8DADC] text-[#2A3B4C] font-semibold shadow-md' 
                  : 'text-slate-300 hover:bg-[#A8DADC]/10 hover:text-white'
              }`}
            >
              <Users className="w-5 h-5" />
              Clientes
            </button>
            <button 
              onClick={() => setActiveTab('contratos')}
              className={`w-full flex items-center gap-3 px-4 py-3 rounded-lg text-sm font-medium transition ${
                activeTab === 'contratos' 
                  ? 'bg-[#A8DADC] text-[#2A3B4C] font-semibold shadow-md' 
                  : 'text-slate-300 hover:bg-[#A8DADC]/10 hover:text-white'
              }`}
            >
              <FileText className="w-5 h-5" />
              Contratos
            </button>
          </nav>
        </div>

        {/* User Profile & Logout */}
        <div className="p-4 border-t border-[#2A3B4C]/40 bg-[#1F2C39]">
          <div className="flex items-center gap-3 mb-3">
            <div className="w-9 h-9 rounded-full bg-[#A8DADC] text-[#2A3B4C] font-bold flex items-center justify-center text-sm shadow">
              JD
            </div>
            <div className="overflow-hidden">
              <p className="text-sm font-semibold truncate text-white">Juan Diego</p>
              <p className="text-xs text-slate-400 truncate">admin@cordevro.com</p>
            </div>
          </div>
          <button className="w-full flex items-center justify-center gap-2 py-2 px-3 rounded-md bg-red-500/10 text-red-400 hover:bg-red-500/20 text-xs font-medium transition">
            <LogOut className="w-4 h-4" />
            Cerrar Sesión
          </button>
        </div>
      </aside>

      {/* ----------------- CONTENIDO PRINCIPAL ----------------- */}
      <div className="flex-1 flex flex-col overflow-hidden">
        
        {/* HEADER SUPERIOR */}
        <header className="h-16 bg-white border-b border-slate-200 px-8 flex items-center justify-between shadow-sm">
          <div className="relative w-72">
            <Search className="w-4 h-4 absolute left-3 top-1/2 -translate-y-1/2 text-slate-400" />
            <input 
              type="text" 
              placeholder="Buscar propiedades, clientes..." 
              className="w-full pl-9 pr-4 py-1.5 bg-[#E0E6ED]/50 border border-slate-200 rounded-lg text-sm focus:outline-none focus:border-[#2A3B4C]"
            />
          </div>

          <div className="flex items-center gap-4">
            <button className="relative p-2 text-slate-600 hover:text-[#2A3B4C] hover:bg-slate-100 rounded-full transition">
              <Bell className="w-5 h-5" />
              <span className="absolute top-1 right-1 w-2 h-2 bg-[#A8DADC] rounded-full border border-white"></span>
            </button>
            <button className="flex items-center gap-2 px-4 py-2 bg-[#2A3B4C] hover:bg-[#1F2C39] text-white text-sm font-medium rounded-lg shadow-md transition">
              <Plus className="w-4 h-4 text-[#A8DADC]" />
              Nuevo Inmueble
            </button>
          </div>
        </header>

        {/* BODY CON SCROLL */}
        <main className="flex-1 overflow-y-auto p-8 space-y-6">
          
          {/* Tarjeta de Bienvenida */}
          <div className="bg-gradient-to-r from-[#2A3B4C] to-[#1F2C39] rounded-2xl p-6 text-white shadow-xl relative overflow-hidden">
            <div className="relative z-10 max-w-xl">
              <span className="px-3 py-1 bg-[#A8DADC]/20 text-[#A8DADC] text-xs font-semibold rounded-full inline-block mb-3 border border-[#A8DADC]/30">
                Soluciones Digitales Inmobiliarias
              </span>
              <h2 className="text-2xl font-bold mb-2">¡Hola de nuevo, Juan! 👋</h2>
              <p className="text-slate-300 text-sm">
                Tienes 4 citas programadas para hoy y 3 nuevas solicitudes de información en tus inmuebles destacados.
              </p>
            </div>
            {/* Isotipo gigante sutil de fondo */}
            <svg className="absolute -right-10 -bottom-10 w-64 h-64 text-[#A8DADC]/5" viewBox="0 0 100 100" fill="none" stroke="currentColor" strokeWidth="4">
              <polygon points="50,5 90,27.5 90,72.5 50,95 10,72.5 10,27.5" />
              <polyline points="38,40 25,50 38,60" />
              <polyline points="62,40 75,50 62,60" />
            </svg>
          </div>

          {/* Tarjetas de Métricas (KPIs) */}
          <div className="grid grid-cols-1 md:grid-cols-4 gap-5">
            <div className="bg-white p-5 rounded-xl shadow-sm border border-slate-200">
              <div className="flex items-center justify-between mb-3">
                <span className="text-xs font-semibold text-slate-500 uppercase">Inmuebles Activos</span>
                <div className="p-2 bg-[#E0E6ED] rounded-lg text-[#2A3B4C]">
                  <Building2 className="w-5 h-5" />
                </div>
              </div>
              <p className="text-2xl font-bold text-[#2A3B4C]">42</p>
              <span className="text-xs font-medium text-emerald-600 flex items-center gap-1 mt-1">
                <TrendingUp className="w-3 h-3" /> +12% este mes
              </span>
            </div>

            <div className="bg-white p-5 rounded-xl shadow-sm border border-slate-200">
              <div className="flex items-center justify-between mb-3">
                <span className="text-xs font-semibold text-slate-500 uppercase">Alquileres</span>
                <div className="p-2 bg-[#A8DADC]/20 rounded-lg text-[#2A3B4C]">
                  <Key className="w-5 h-5" />
                </div>
              </div>
              <p className="text-2xl font-bold text-[#2A3B4C]">28</p>
              <span className="text-xs font-medium text-slate-500 mt-1 block">85% Ocupación</span>
            </div>

            <div className="bg-white p-5 rounded-xl shadow-sm border border-slate-200">
              <div className="flex items-center justify-between mb-3">
                <span className="text-xs font-semibold text-slate-500 uppercase">Clientes Interesados</span>
                <div className="p-2 bg-[#E0E6ED] rounded-lg text-[#2A3B4C]">
                  <Users className="w-5 h-5" />
                </div>
              </div>
              <p className="text-2xl font-bold text-[#2A3B4C]">154</p>
              <span className="text-xs font-medium text-emerald-600 flex items-center gap-1 mt-1">
                <TrendingUp className="w-3 h-3" /> +8 nuevos hoy
              </span>
            </div>

            <div className="bg-white p-5 rounded-xl shadow-sm border border-slate-200">
              <div className="flex items-center justify-between mb-3">
                <span className="text-xs font-semibold text-slate-500 uppercase">Ingresos Estimados</span>
                <div className="p-2 bg-[#2A3B4C] rounded-lg text-[#A8DADC]">
                  <TrendingUp className="w-5 h-5" />
                </div>
              </div>
              <p className="text-2xl font-bold text-[#2A3B4C]">18.450 €</p>
              <span className="text-xs font-medium text-slate-500 mt-1 block">Cobros del mes</span>
            </div>
          </div>

          {/* Tabla de Ejemplo de Inmuebles */}
          <div className="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
            <div className="p-5 border-b border-slate-100 flex justify-between items-center">
              <div>
                <h3 className="font-bold text-lg text-[#2A3B4C]">Últimas Propiedades Añadidas</h3>
                <p className="text-xs text-slate-500">Gestión rápida del catálogo de inmuebles</p>
              </div>
              <button className="text-xs font-semibold text-[#2A3B4C] hover:text-[#A8DADC] flex items-center gap-1 transition">
                Ver todos <ChevronRight className="w-4 h-4" />
              </button>
            </div>

            <table className="w-full text-left text-sm">
              <thead className="bg-[#E0E6ED]/40 text-[#2A3B4C] font-semibold text-xs uppercase">
                <tr>
                  <th className="p-4">Propiedad</th>
                  <th className="p-4">Tipo</th>
                  <th className="p-4">Precio</th>
                  <th className="p-4">Estado</th>
                  <th className="p-4 text-right">Acciones</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-slate-100">
                <tr className="hover:bg-slate-50/80 transition">
                  <td className="p-4 font-medium text-[#2A3B4C]">Ático en Centro Histórico</td>
                  <td className="p-4 text-slate-500">Venta</td>
                  <td className="p-4 font-semibold text-[#2A3B4C]">245.000 €</td>
                  <td className="p-4">
                    <span className="px-2.5 py-1 bg-emerald-100 text-emerald-800 text-xs font-medium rounded-full">
                      Disponible
                    </span>
                  </td>
                  <td className="p-4 text-right">
                    <button className="text-xs font-medium text-[#2A3B4C] hover:underline">Editar</button>
                  </td>
                </tr>
                <tr className="hover:bg-slate-50/80 transition">
                  <td className="p-4 font-medium text-[#2A3B4C]">Chalet Adosado con Jardín</td>
                  <td className="p-4 text-slate-500">Alquiler</td>
                  <td className="p-4 font-semibold text-[#2A3B4C]">1.200 €/mes</td>
                  <td className="p-4">
                    <span className="px-2.5 py-1 bg-amber-100 text-amber-800 text-xs font-medium rounded-full">
                      Reservado
                    </span>
                  </td>
                  <td className="p-4 text-right">
                    <button className="text-xs font-medium text-[#2A3B4C] hover:underline">Editar</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

        </main>

        {/* ----------------- FOOTER CON BRANDING CORDEVRO ----------------- */}
        <footer className="bg-white border-t border-slate-200 px-8 py-3 flex items-center justify-between text-xs text-slate-500">
          <p>© 2026 InmoSaaS. Todos los derechos reservados.</p>
          
          {/* Logo Cordevro en el Footer */}
          <div className="flex items-center gap-2">
            <span>Powered by</span>
            <div className="flex items-center gap-1.5 font-bold text-[#2A3B4C]">
              {/* Isotipo Vectorial Pequeño */}
              <svg className="w-4 h-4 text-[#2A3B4C]" viewBox="0 0 100 100" fill="none" stroke="currentColor" strokeWidth="10" strokeLinecap="round" strokeLinejoin="round">
                <polygon points="50,5 90,27.5 90,72.5 50,95 10,72.5 10,27.5" />
                <polyline points="38,40 25,50 38,60" />
                <polyline points="62,40 75,50 62,60" />
                <line x1="53" y1="36" x2="47" y2="64" />
              </svg>
              <span>cordevro</span>
            </div>
            <span className="text-[10px] text-[#A8DADC] bg-[#2A3B4C] px-1.5 py-0.5 rounded font-semibold uppercase tracking-wider">
              Soluciones Digitales
            </span>
          </div>
        </footer>

      </div>
    </div>
  );
}

export default AppPrueba;