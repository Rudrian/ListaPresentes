function carregarTarefas() {
    $.ajax({
        url: '/ListaPresentes/',
        type: 'GET',
        success: function (tarefas) {
            let lista = '';
            tarefas.forEach(presenca => {
                lista += `
                    <li class="list-group-item">
                        <strong>${presenca.nome}</strong><br>
                        <small>${presenca.cargo || ''}</small><br>
                        <small>${presenca.empresa || ''}</small>
                    </li>
                `;
            });
            $('#listaTarefas').html(lista);
        }
    });
}

function adicionarTarefa() {
    const tarefa = {
        nome: $('#nomePresenca').val(),
        cargo: $('#cargoPresenca').val(),
        empresa: $('#empresaPresenca').val()
    };

    $.ajax({
        url: '/ListaPresentes/',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(tarefa),
        success: function () {
            $('#formTarefa')[0].reset();
            carregarTarefas();
        },
        error: function (xhr) {
            console.error("Erro ao adicionar tarefa:", xhr.responseText);
        }
    });
}

$(document).ready(function () {
    carregarTarefas();
});
