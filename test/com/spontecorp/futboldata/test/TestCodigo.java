/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.test;

/**
 *
 * @author jgcastillo
 */
public class TestCodigo {
//    private ArrayList<Pair<String, Integer>> getInspeccionesNecesariasConDias() {
//        final ArrayList<Pair<String, Integer>> ret = new ArrayList<Pair<String, Integer>>();
//        for (Inspeccion item : getInspeccionesNecesarias()) {
//            Boolean esta = false;
//            for (HistorialInspecciones his : historialInsp) {
//                if (his.pk == item.getPk()) {
//                    esta = true;
//                    Calendar today = Calendar.getInstance();
//                    Calendar hisFe = Calendar.getInstance();
//                    hisFe.setTime(his.fecha);
//                    final long diff = today.getTimeInMillis()
//                            - hisFe.getTimeInMillis();
//                    Log.v(TAG, "Fecha de la inspeccion registrada: " + his.fecha);
//                    final int diffDays = (int) (diff / (24 * 60 * 60 * 1000) - item.getLoop());
//                    /**
//                     * ***** convierte en dias
//                     */
//                    final String print = " Pk: " + getPk()
//                            + " Ins: " + item.getNombreInspeccion()
//                            + " dias sin restarle loop: " + (diffDays + item.getLoop())
//                            + " dias: " + diffDays
//                            + " loop: " + item.getLoop()
//                            + "";
//                    Log.d(TAG, print);
//                    final Pair<String, Integer> ad = new Pair<String, Integer>(item.getNombreInspeccion(), item.getColor(diffDays));
//                    ret.add(ad);
//                    break;
//                }
//            }
//            if (!esta) {
//                ret.add(new Pair<String, Integer>(item.getNombreInspeccion(), item.getMaxColor()));
//                contador++;
//                Log.d("GET MAX COLOR****************", "el numero de pintadas es: " + Integer.toString(contador));
//            }
//        }
//        return ret;

//    }
//    public ArrayList<Inspeccion> getInspeccionesNecesarias(boolean nec) {
//        ArrayList<Inspeccion> ret = new ArrayList<Inspeccion>();
//        if (historialInsp == null) {
//            historialInsp = new ArrayList<VehicleInspector.HistorialInspecciones>();
//        }
//        for (TipoInspeccion item : insp) {
//            Boolean esta = false;
//            Inspeccion in; //Objeto Inspeccion (descripcion, edo motor y ¿¿pk??)
//            if (isVentanaInspector) {
//                in = InspectorVehiclesListActivity.inRet.getInspeccionByPk(item.pk); //
//            } else {
//                in = BuscarPlaca.insp.getInspeccionByPk(item.pk);
//            }
//            for (int i = 0; i < historialInsp.size() && !esta; i++) {
//                HistorialInspecciones his = historialInsp.get(i);
//                if (his.pk == item.pk) {
//                    esta = true;
//                    Calendar today = Calendar.getInstance();
//                    Calendar hisFe = Calendar.getInstance();
//                    hisFe.setTime(his.fecha);
//                    long diff = today.getTimeInMillis() - hisFe.getTimeInMillis();
//                    int diffDays = (int) diff / (24 * 60 * 60 * 1000);
//                    int loop = in.getLoop();
//                    if (diffDays > loop) {
//                        ret.add(in);
//                    }
//                }
//            }
//            if (!esta) {
//                ret.add(in);
//            }
//        }
//        return ret;
//    }
    
    
    
    
//    public ArrayList<Inspeccion> getInspeccionesNecesarias() {
//        ArrayList<Inspeccion> ret = new ArrayList<Inspeccion>();
//        //validacion 
//        if (historialInsp == null) {
//            historialInsp = new ArrayList<VehicleInspector.HistorialInspecciones>();
//        }
//        for (TipoInspeccion item : insp) { // TipoInspeccion contiene el String Tipo y el Int PK de los tipos inspecciones que se le pueden hacer al vehiculo.
//            Boolean esta = false;
//            Inspeccion in; // el Objeto "in" contendra todas las caracteristicas de los 4 tipos de inspecciones, como el LOOP.
//            if (isVentanaInspector) {
//                in = InspectorVehiclesListActivity.inRet.getInspeccionByPk(item.pk); //in ya posee la info de las inspecciones.
//            } else {
//                in = BuscarPlaca.insp.getInspeccionByPk(item.pk);
//            }
//            for (int i = 0; i < historialInsp.size() && !esta; i++) {
//                HistorialInspecciones his = historialInsp.get(i); // historialInsp contiene la fecha de la ultima inspeccion de un PK determinado.
//                if (his.pk == item.pk) {
//                    esta = true;    // coincide pk posibles inspecciones con pk del historial, para comprobar fecha.
//                    Calendar today = Calendar.getInstance();
//                    Calendar hisFe = Calendar.getInstance();
//                    hisFe.setTime(his.fecha);
//                    long diff = today.getTimeInMillis() - hisFe.getTimeInMillis(); //Se obtiene la fecha total en milisegundos y se le resta la fecha de la ultima revision
//                    int diffDays = (int) diff / (24 * 60 * 60 * 1000); // ***** en una corrida me dio -17 *****
//
//                    int loop = in.getLoop();
//                    if (diffDays > loop) {  //esta linea esta fallando
//                        ret.add(in);
//                    }
//                }
//            }
//            if (!esta) {
//                ret.add(in);
//            }
//        }
//        return ret;
//
//        /**
//         * *******************************SE EJECUTA DE 3RO JUNTO CON
//         * MEOLLO*************************************
//         */
//        @Override
//        public ArrayList<LinearLayout> getStatus(Context context){
//       
//            ArrayList<LinearLayout> ret = new ArrayList<LinearLayout>();
//            for (Pair<String, Integer> item : getInspeccionesNecesariasConDias()) {
//                LinearLayout ll = new LinearLayout(context);
//                TextView txv = new TextView(context);
//                Bitmap bmp = Bitmap.createBitmap(15, 15, Bitmap.Config.RGB_565);
//                Canvas c = new Canvas(bmp);
//                Paint p = new Paint();
//                Log.d("*************************AUXILIO*************************", item.first);
//                final String color = "#" + Integer.toHexString(item.second); // COLOR VERDE 00FF00
//                p.setColor(Color.parseColor(color));
//                c.drawPaint(p);
//                c.drawCircle(2, 2, 0, p);
//                ImageView img = new ImageView(context);
//                img.setImageDrawable(new BitmapDrawable(context.getResources(), bmp));
//                img.setPadding(5, 14, 2, 0);
//                txv.setText(item.first.charAt(0) + "");
//                txv.setTextColor(Color.parseColor("#000000"));
//                txv.setTextSize((float) 21);
//                txv.setGravity(Gravity.RIGHT);
//                ll.addView(img);
//                ll.addView(txv);
//                ret.add(ll);
//            }
//            return ret;
//        }
//        /**
//         * ******************** MEOLLO *********************
//         */
//    private ArrayList<Pair<String, Integer>> getInspeccionesNecesariasConDias() {
//        final ArrayList<Pair<String, Integer>> ret = new ArrayList<Pair<String, Integer>>();
//        for (Inspeccion item : getInspeccionesNecesarias()) {
//            Boolean esta = false;
//            for (HistorialInspecciones his : historialInsp) {
//                if (his.pk == item.getPk()) {
//                    esta = true;
//                    Calendar today = Calendar.getInstance();
//                    Calendar hisFe = Calendar.getInstance();
//                    hisFe.setTime(his.fecha);
//                    final long diff = today.getTimeInMillis()
//                            - hisFe.getTimeInMillis();
//                    Log.v(TAG, "Fecha de la inspeccion registrada: " + his.fecha);
//                    final int diffDays = (int) (diff / (24 * 60 * 60 * 1000) - item.getLoop());
//                    /**
//                     * ***** convierte en dias
//                     */
//                    final String print = " Pk: " + getPk()
//                            + " Ins: " + item.getNombreInspeccion()
//                            + " dias sin restarle loop: " + (diffDays + item.getLoop())
//                            + " dias: " + diffDays
//                            + " loop: " + item.getLoop()
//                            + "";
//                    Log.d(TAG, print);
//                    final Pair<String, Integer> ad = new Pair<String, Integer>(item.getNombreInspeccion(), item.getColor(diffDays));
//                    ret.add(ad);
//                    break;
//                }
//            }
//            if (!esta) {
//                ret.add(new Pair<String, Integer>(item.getNombreInspeccion(), item.getMaxColor()));
//                contador++;
//                Log.d("GET MAX COLOR****************", "el numero de pintadas es: " + Integer.toString(contador));
//            }
//        }
//        return ret;
//    }

}
