window.addEventListener('load', () => {

    //Todos los toDo - Se piden todas las tareas a la API https://ctd-todo-api.herokuapp.com/

    const urlTraertodos = '/turnos';

    const setting = {
        method: 'GET'
    };

    function traerTurnos() {
        fetch(urlTraertodos, setting)
            .then((response) => response.json())
            .then((json) => dibujarTurnos(json))
            .catch((e) => console.log(e))
    }
    traerTurnos()
    //template de un turno
    let nuevoTurno = turno =>
        `
    <tr>
        <th id="id_${turno.id}" scope="row">${turno.id}</th>
        <td id="pacienteNombre_${turno.id}">${turno.paciente.nombre} ${turno.paciente.apellido}</td>
        <td id="dni_${turno.id}">${turno.paciente.dni}</td>
        <td id="odontologoNombre_${turno.id}">${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
        <td id="fechaTurno_${turno.id}">${turno.fechaTurno}</td>
        <td id="horaTurno_${turno.id}">${turno.horaTurno}</td>
        <td>
            <a type="button" onclick="updateBy(${turno.id})" href="#">
            <i class="fas fa-edit"></i>
            </a> 
            
            <a type="button" onclick="deleteBy(${turno.id})" href="#">
            <i class="fas fa-user-times"></i>
            </a>

        </td>
    </tr>
        `

    // Renderizar totas los turnos obtenidos
    function dibujarTurnos(arreglo) {
        const turnos = document.querySelector('#turnos');
        turnos.innerHTML = "";
        arreglo.forEach(turno => {
            turnos.innerHTML += nuevoTurno(turno)
        })
    }

    /* ************************Subir formulario********************** */
    const formulario = document.querySelector('#actionspaciente');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        
        const formData = {
            id: document.querySelector('#id_mod').value,
            fechaTurno: document.querySelector('#fechaturno_mod').value,
            horaTurno: document.querySelector('#horaturno_mod').value,
            odontologo: {
                id: document.querySelector('#nodontologo_mod').value
            },
            paciente: {
                id: document.querySelector('#npaciente_mod').value
            }
        };
        
console.log(document.querySelector('#id_mod').value);
console.log(document.querySelector('#fechaturno_mod').value);
console.log(document.querySelector('#horaturno_mod').value);
console.log(document.querySelector('#nodontologo_mod').value);
console.log(document.querySelector('#npaciente_mod').value);

        const urlregistrarturno = '/turnos/registrar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(urlregistrarturno, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno agregado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
                location.reload();

            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
                location.reload();
            })
    });

    function resetUploadForm(){
        document.querySelector('#id_mod').value = "",
        document.querySelector('#fechaturno_mod').value = "",
        document.querySelector('#horaturno_mod').value = "",
        document.querySelector('#nodontologo_mod').value = "",
        document.querySelector('#npaciente_mod').value = ""
    }
    /* ************************************************************** */

})


function deleteBy(id) {
    const url = '/turnos/' + id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url, settings)
        .then(response => response.json())
    location.reload();
}



function updateBy(id) {
    document.getElementById('list').className = 'tab-pane fade';
    document.getElementById('form').className = 'tab-pane fade show active';

    const urlTraertodos = '/turnos/' + id;

    const setting = {
        method: 'GET'
    };

    function traerTurno() {
        fetch(urlTraertodos, setting)
            .then((response) => response.json())
            .then((json) => llenarcampos(json))
            .catch((e) => console.log(e))
    }
    traerTurno()

    function llenarcampos(data) {
        document.getElementById('id_mod').value = document.getElementById('id_' + id).textContent;
        document.getElementById('npaciente_mod').value = `${document.getElementById('pacienteNombre_' + id).textContent} - DNI: ${document.getElementById('dni_' + id).textContent}`;
        // document.getElementById('dni_mod').value = document.getElementById('dni_' + id).textContent;
        // document.getElementById('nodontologo_mod').value = document.getElementById('odontologoNombre_' + id).textContent;
        document.getElementById('fechaturno_mod').value = document.getElementById('fechaTurno_' + id).textContent;
        document.getElementById('horaturno_mod').value = document.getElementById('horaTurno_' + id).textContent;

        const urlTraerodontologos = '/odontologos'
        const setting = {
            method: 'GET'
        };
        function traerOdontologos() {
            fetch(urlTraerodontologos, setting)
                .then((response) => response.json())
                .then((json) => llenarodontologos(json))
                .catch((e) => console.log(e))
        }
        traerOdontologos()

        const urlTraerpacientes = '/pacientes'


        function traerPacientes() {
            fetch(urlTraerpacientes, setting)
                .then((response) => response.json())
                .then((json) => llenarpacientes(json))
                .catch((e) => console.log(e))
        }
        traerPacientes()


    }
}

const setting = {
    method: 'GET'
};



function llenarodontologos(data) {
    const odontologos = document.querySelector('#nodontologo_mod');
    odontologos.innerHTML = "";
    data.forEach(odontologo => {
        odontologos.innerHTML += `<option value="${odontologo.id}">${odontologo.nombre} ${odontologo.apellido}</option>`
    }
    )
}

function llenarpacientes(data) {
    const pacientes = document.querySelector('#npaciente_mod');
    pacientes.innerHTML = "";
    data.forEach(paciente => {
        pacientes.innerHTML += `<option value="${paciente.id}">${paciente.nombre} ${paciente.apellido} - DNI: ${paciente.dni}</option>`
    }
    )
}

const urlTraerodontologos = '/odontologos'
const urlTraerpacientes = '/pacientes'

function traerOdontologoypacientes() {

    function traerOdontologos() {
        fetch(urlTraerodontologos, setting)
            .then((response) => response.json())
            .then((json) => llenarodontologos(json))
            .catch((e) => console.log(e))
    }
    traerOdontologos()

    function traerPacientes() {
        fetch(urlTraerpacientes, setting)
            .then((response) => response.json())
            .then((json) => llenarpacientes(json))
            .catch((e) => console.log(e))
    }
    traerPacientes()

    function llenarodontologos(data) {
        const odontologos = document.querySelector('#nodontologo_mod');
        odontologos.innerHTML = "";
        data.forEach(odontologo => {
            odontologos.innerHTML += `<option value="${odontologo.id}">${odontologo.nombre} ${odontologo.apellido}</option>`
        }
        )
    }

    function llenarpacientes(data) {
        const pacientes = document.querySelector('#npaciente_mod');
        pacientes.innerHTML = "";
        data.forEach(paciente => {
            pacientes.innerHTML += `<option value="${paciente.id}">${paciente.nombre} ${paciente.apellido} - DNI: ${paciente.dni}</option>`
        }
        )
    }
}