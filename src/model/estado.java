package model;
/**
 * Estados posibles de un viaje dentro del sistema.
 * <ul>
 *     <li>PENDIENTE: el viaje fue creado pero aún no inició.</li>
 *     <li>EN_CURSO: el viaje está en ejecución.</li>
 *     <li>FINALIZADO: el viaje ya fue completado.</li>
 * </ul>
 */
public enum estado{
    PENDIENTE,EN_CURSO, FINALIZADO
}
