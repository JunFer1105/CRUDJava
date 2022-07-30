/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function crearTablaJson(data) {
    if (data.length === undefined) {//Si llega un solo objeto
        dataArray = [];
        dataArray.push(data);
        data = dataArray;
    }

    var cols = [];
    for (var i = 0; i < data.length; i++) {
        for (var k in data[i]) {
            if (cols.indexOf(k) === -1) {
                // Añadimos las llaves al arreglo de la cabecera
                cols.push(k);
            }
        }
    }
    // Creamos el elemento tabla
    var table = document.createElement("table");
    table.setAttribute("id", "tabla_resultado");
    // Creamos los contenedores thead, tbody y los llenamos
    var thead = document.createElement("thead");
    var tr_thead = document.createElement("tr");
    for (var i = 0; i < cols.length; i++) {
        var theader = document.createElement("th");
        theader.innerHTML = cols[i];
        tr_thead.appendChild(theader);
    }
    thead.appendChild(tr_thead);
    table.appendChild(thead);
    var tbody = document.createElement("tbody");

    // Agregamos los datos al cuerpo de la tabla
    for (var i = 0; i < data.length; i++) {
        trow = document.createElement("tr");
        ;
        for (var j = 0; j < cols.length; j++) {
            tcol = document.createElement("td");
            ;
            tcol.innerHTML = data[i][cols[j]];
            trow.appendChild(tcol);
        }
        tbody.appendChild(trow);
    }
    table.appendChild(tbody);
    // Añadimos la nueva tabla JSON al modal
    var cuerpoModal = document.getElementById("responseModal_body");
    cuerpoModal.innerHTML = "";
    cuerpoModal.appendChild(table);
    $('#tabla_resultado').DataTable();

    $('#responseModal').modal('show');
}

function crearRespuesta(data) {
    var cuerpoModal = document.getElementById("responseModal_body");
    cuerpoModal.innerHTML = data;
    $('#responseModal').modal('show');
}

function getAllEspecialidades() {
    fetch('especialidad')
            .then(response => response.json())
            .then(data => {
                crearTablaJson(data);

            });
}

function getAllMedicos() {
    fetch('medico')
            .then(response => response.json())
            .then(data => {
                crearTablaJson(data);

            });
}

function getEspecialidadesPorId(id_especialidad) {
    var id = id_especialidad.value;
    console.log(id);
    fetch('especialidad?id=' + id, {
        method: 'GET'
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                crearTablaJson(data);

            });
}

function insertarMedico(id_medico, nombre_medico, telefono_medico, direccion_medico, fec_nacimiento_medico,
        id_especialidad) {
    var formData = new FormData();

    formData.append("id_medico", id_medico.value);
    formData.append("nombre", nombre_medico.value);
    formData.append("telefono", telefono_medico.value);
    formData.append("direccion", direccion_medico.value);
    formData.append("fec_nacimiento", fec_nacimiento_medico.value);
    formData.append("id_especialidad", id_especialidad.value);

    fetch('medico', {
        method: 'POST',
        body: formData
    }).then(response => response.text())
        .then(data=> {
        crearRespuesta(data);
    });
}

function actualizarMedico(id_medico, nombre_medico, telefono_medico, direccion_medico, fec_nacimiento_medico,
        id_especialidad) {
    var formData = new FormData();

    formData.append("id_medico", id_medico.value);
    formData.append("nombre", nombre_medico.value);
    formData.append("telefono", telefono_medico.value);
    formData.append("direccion", direccion_medico.value);
    formData.append("fec_nacimiento", fec_nacimiento_medico.value);
    formData.append("id_especialidad", id_especialidad.value);
    formData.append("activo", "true");

    fetch('medico', {
        method: 'PUT',
        body: formData
    }).then(response => response.text())
        .then(data=> {
        crearRespuesta(data);
    });
}

function getMedicoPorEspecialidad(id_especialidad) {
    var id = id_especialidad.value;
    console.log(id);
    fetch('medico?id_especialidad=' + id, {
        method: 'GET'
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                crearTablaJson(data);

            });
}

function deleteMedico(id_medico){
    var formData = new FormData();
    formData.append("id_medico", id_medico.value);
    
    fetch('medico', {
        method: 'DELETE',
        body: formData
    }).then(response => response.text())
        .then(data=> {
        crearRespuesta(data);
    });
}
