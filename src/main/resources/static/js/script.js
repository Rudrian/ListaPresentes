function carregarPresenca() {
    $.ajax({
        url: '/ListaPresentes/',
        type: 'GET',
        success: function (presenca) {
            let lista = '';
            presenca.forEach(item => {
                lista += `
                    <li class="list-group-item">
                        <strong>${item.nome}</strong><br>
                        <small>${item.cargo || ''}</small><br>
                        <small>${item.empresa || ''}</small>
                    </li>
                `;
            });
            $('#listaPresenca').html(lista);
        }
    });
}

function adicionarPresenca() {
    const presenca = {
        nome: $('#nomePresenca').val(),
        cargo: $('#cargoPresenca').val(),
        empresa: $('#empresaPresenca').val()
    };

    $.ajax({
        url: '/ListaPresentes/',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(presenca),
        success: function () {
            $('#formPresenca')[0].reset();
            carregarPresenca();
        },
        error: function (xhr) {
            console.error("Erro ao adicionar presença:", xhr.responseText);
        }
    });
}

$(document).ready(function () {
    carregarPresenca();
});
