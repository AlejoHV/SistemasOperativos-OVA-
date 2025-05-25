#include "com_example_sistemas_ovasistemaso_service_NativeSJF.h"
#include <jni.h>
#include <stdio.h>
#include <stdlib.h>

/*
 * Class:     com_example_sistemas_ovasistemaso_service_NativeSJF
 * Method:    scheduleSJF
 * Signature: ([I[I[I)[I
 */

typedef struct {
    int id;
    int arrival;
    int burst;
} Process;

// Implementación REAL de schedule_sjf (elimina 'extern')
int* schedule_sjf(Process* processes, int n) {
    // Algoritmo SJF (Ordenar por burst time)
    for (int i = 0; i < n-1; i++) {
        for (int j = 0; j < n-i-1; j++) {
            if (processes[j].burst > processes[j+1].burst) {
                Process temp = processes[j];
                processes[j] = processes[j+1];
                processes[j+1] = temp;
            }
        }
    }

    // Crear array con orden de ejecución (IDs)
    int* order = (int*)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        order[i] = processes[i].id;
    }
    return order;
}


JNIEXPORT jintArray JNICALL Java_com_example_sistemas_ovasistemaso_service_NativeSJF_scheduleSJF
  (JNIEnv *env, jobject obj, jintArray ids, jintArray arrivals, jintArray bursts) {

  // Convertir arrays de Java a C
  jint *c_ids = (*env)->GetIntArrayElements(env, ids, NULL);
  jint *c_arrivals = (*env)->GetIntArrayElements(env, arrivals, NULL);
  jint *c_bursts = (*env)->GetIntArrayElements(env, bursts, NULL);
  jsize n = (*env)->GetArrayLength(env, ids);

  // Crear array de procesos
  Process *processes = (Process *)malloc(n * sizeof(Process));
  for (int i = 0; i < n; i++) {
      processes[i].id = c_ids[i];
      processes[i].arrival = c_arrivals[i];
      processes[i].burst = c_bursts[i];  // Corregido: = en lugar de -
  }

  // Llamar a SJF
  int *order = schedule_sjf(processes, n);

  // Convertir resultado a Java
  jintArray result = (*env)->NewIntArray(env, n);
  (*env)->SetIntArrayRegion(env, result, 0, n, order);

  // Liberar memoria
  free(processes);
  (*env)->ReleaseIntArrayElements(env, ids, c_ids, 0);
  (*env)->ReleaseIntArrayElements(env, arrivals, c_arrivals, 0);
  (*env)->ReleaseIntArrayElements(env, bursts, c_bursts, 0);

   return result;
}
