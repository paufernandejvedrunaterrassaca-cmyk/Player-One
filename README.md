## VIDEO ACTIVITAT 5 I 6: https://youtu.be/t3jjr4glgDs

# DOCUMENTACIÓ DEL CODI ACTIVITAT 6

1. SplashScreen i animació del logotip

En iniciar l'aplicació hem implementat una SplashScreen, que és la primera pantalla que es mostra abans d'entrar a l'aplicació.

Per personalitzar-la hem utilitzat el logotip de Player1 i li hem afegit una animació. Per fer-ho, hem utilitzat un logotip vectorial i un objectAnimator que permet controlar el moviment.

L'animació fa que el logotip giri 360 graus cap a l'esquerra durant un segon. També hem configurat el tema de la SplashScreen perquè, quan acaba, es carregui el tema principal de l'aplicació.

2. Adapter i ViewHolder

Per mostrar les missions de l'aplicació hem utilitzat un RecyclerView, que ens permet tenir una llista de missions i actualitzar-la quan afegim, modifiquem o eliminem elements.

Per fer funcionar aquesta llista hem creat dues classes: MisionAdapter i MisionViewHolder.

La classe MisionAdapter connecta les dades amb la llista. Per fer-ho, utilitza diferents mètodes:

- onCreateViewHolder(): crea els elements de la llista utilitzant el disseny item_mision.xml.
- onBindViewHolder(): passa les dades de cada missió al seu element corresponent.
- getItemCount(): indica quantes missions hi ha per mostrar.

També hem afegit el paràmetre mostrarNomésCreades per poder mostrar totes les missions o només les que ha creat l'usuari.

La classe MisionViewHolder s'encarrega de mostrar la informació de cada missió. Amb View Binding accedim als diferents elements del disseny, com el títol, la descripció, l'emoji, el progrés, l'estat i les recompenses.

Dins del mètode bind() assignem les dades i calculem el percentatge de progrés de cada missió. També controlem el seu estat per mostrar el text i el color corresponents.

A més, hem programat les accions de cada missió. Quan se selecciona una, es registra l'acció amb Log.d(). Si és una missió creada per l'usuari, també es pot obrir un menú amb les opcions d'editar i eliminar.

Per gestionar els canvis de la llista hem creat tres mètodes a l'Adapter:

- add(), per afegir una missió.
- update(), per modificar una missió.
- removeAt(), per eliminar una missió.

Després de fer aquests canvis utilitzem notifyDataSetChanged() perquè la llista s'actualitzi i mostri les dades noves. També utilitzem Log.d() per deixar constància de les operacions realitzades.

3. Programació dels menús

Per poder moure'ns per les diferents pantalles de l'aplicació hem utilitzat una Toolbar a la part superior i un BottomNavigationView a la part inferior.

La Toolbar es troba definida a activity_main.xml i la configurem des de MainActivity.kt amb setSupportActionBar(). Les opcions del menú superior estan definides al fitxer top_menu.xml, que es troba dins de res/menu.

A MainActivity.kt utilitzem onCreateOptionsMenu() per mostrar el menú i onOptionsItemSelected() per detectar quina opció ha seleccionat l'usuari i executar l'acció corresponent.

Per a la navegació inferior hem utilitzat BottomNavigationView. Cada opció permet accedir a una secció diferent de l'aplicació. Per mostrar les pantalles utilitzem Fragments, de manera que podem canviar de secció sense haver de crear una Activity nova per a cada pantalla.

També hem incorporat un PopupMenu a les missions creades per l'usuari. Aquest menú s'obre quan es prem el botó dels tres punts que apareix a cada missió.

Per crear-lo utilitzem PopupMenu, afegim les opcions amb menu.add() i controlem quina s'ha seleccionat amb setOnMenuItemClickListener(). Si l'usuari selecciona editar, obrim la pantalla de modificació de la missió; si selecciona eliminar, es crida el mètode corresponent per treure-la de la llista.

D'aquesta manera, hem pogut organitzar la navegació i les diferents opcions de l'aplicació perquè siguin fàcils d'utilitzar.
