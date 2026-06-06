package com.traceon.traceonapi.maintenance.domain.exception;

public class ChecklistPendenteException
        extends RuntimeException {

    public ChecklistPendenteException() {

        super(
                "Todos os itens do checklist devem estar concluídos."
        );

    }

}