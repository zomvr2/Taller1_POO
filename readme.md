# Contador de Procrastinación

## ¿Qué es esto?

Una aplicación que rastrea cuánto tiempo gastamos procrastinando. Martín, Catalina y Estefanía querían saber cuántas horas pasan viendo series, reels y otras cosas en lugar de ser productivos. Como su vieja app se dañó, necesitaban alguien que hiciera una nueva.

## Cómo funciona

La app tiene dos menús principales:

1. **Menú de Usuarios**: Aquí entras con tu usuario y contraseña para registrar tu tiempo de procrastinación. Puedes agregar nuevas actividades, modificarlas o eliminarlas.

2. **Menú de Análisis**: Sin necesidad de contraseña, puedes ver estadísticas de todos. ¿Quién procrastina más? ¿Cuál es la actividad favorita? Todo está ahí.

## Estructura de archivos

```
├── readme.md                  (este archivo)
├── src/
│   ├── module-info.java
│   └── taller/
│       └── Main.java          (el programa principal)
├── Usuarios.txt               (quién es quién)
└── Registros.txt              (tus actividades guardadas)
```

### Archivos importantes

**Usuarios.txt**: Lista de usuarios y contraseñas
```
Martin;papurri
Catalina;furryfacto
Estefania;cutiemarks
```

**Registros.txt**: Registro de todo lo que han hecho
```
Catalina;14/10/2025;6;viendo jojos
Martin;15/10/2025;4;viendo reels
Estefania;15/10/2025;3;viendo mlp
```

## Cómo ejecutar

### Desde la consola:
```bash
cd /ruta/del/proyecto
javac src/taller/Main.java
java -cp src taller.Main
```

Eso es. Se abrirá un menú en la consola donde puedes elegir qué hacer.

## Qué puedes hacer

**Si eres usuario:**
- Agregar una nueva actividad de procrastinación
- Cambiar uno de tus registros (si pusiste la fecha mal, por ejemplo)
- Borrar una actividad
- Cambiar tu contraseña

**Si quieres ver estadísticas:**
- La actividad más común en general
- La actividad favorita de cada persona
- Quién es el mayor procrastinador
- Todas las actividades registradas

