package com.veritran.ui.interfaces;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

/**
 * Esta clase permite extender a la plataforma mobile, otorgando el Lifecycle completo del activity
 * que este en curso utilizado por Veritran, y otros eventos importantes al momento de inicializar
 * los modulos.
 */
public interface VeritranModule extends Application.ActivityLifecycleCallbacks {

    /**
     * Este evento onApplicationCreated es llamado cuando el Application ha sido creada y puede ser
     * utilizada para cualquier llamado de lifecycle
     *
     * @param application
     */
    void onApplicationCreated(Application application);

    /**
     * Este evento onModelLoaded es llamado cuando el SDK.init termino completamente sus tareas de
     * inicializacion
     *
     * @param activity
     */
    void onModelLoaded(Activity activity);

    /**
     * Este evento onNewIntent va a ser llamado siempre y cuando se este en un flujo Veritran.
     * Los Modulos furtemente dependientes como Componentes Visuales hacen el llamado correctamente.
     * En los casos de llamados a Procesos fuera del motor con o sin uso de UI el mismo no es llamado
     * por que Veritran no maneja el Activity que lo llama.
     *
     * @param activity
     * @param intent
     */
    void onNewIntent(Activity activity, Intent intent);

    /**
     * Este evento onActivityResult va a ser llamado siempre y cuando se este en un flujo Veritran.
     * Los Modulos furtemente dependientes como Componentes Visuales hacen el llamado correctamente.
     * En los casos de llamados a Procesos fuera del motor con o sin uso de UI el mismo no es llamado
     * por que Veritran no maneja el Activity que lo llama.
     *
     * @param activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data);

}
