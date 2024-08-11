// Для запросов
const url = "http://localhost:8080/api/user";
const urlAdmin = "http://localhost:8080/api/admin/";

// Для шапки
const span = document.getElementById('spanAdmin');
let resultSpan =''
// Для таблици
const adminTable = document.querySelector('tbody')
let resAdminTable =''
// Кнопка создания юзера
const btnCreate = document.getElementById('btnCreate')
// Moдальное окно
const modal = new bootstrap.Modal(document.getElementById('modal'))

//Форма
const form =document.getElementById('modalForm')
const username = document.getElementById('username')
const surname = document.getElementById('surname')
const age = document.getElementById('age')
const email = document.getElementById('email')
const password = document.getElementById('password')
const rolesSelect = document.getElementById('rolesSelect')

//Переменная для определения действия (добавить или обновить)
let option = ''

// Вывод инфирмации в шапке страницы
const showSpanElement = (spanRoles) => {
    resultSpan += `<h5 class="text-white bg-dark">
                    <td>${spanRoles.email}</td>
                    <br>
                    with roles:
                    <td>${getRole(spanRoles)}</td>`
    span.innerHTML = resultSpan
}
// Обращение к юзер котнроллеру для получения инфы о текущем юзере
fetch(url)
    .then(r => r.json())
    .then(data => showSpanElement(data))
    .catch(e => console.log(e))

// Обращение к рест контроллеру админа для получения всех юзеров
fetch(urlAdmin)
    .then(r => r.json())
    .then(data => showAdminTableResult(data))
    .catch(e => console.log(e))


// Вывод таблици
const showAdminTableResult = (users) => {
    users.forEach(user =>{
        resAdminTable += `<tr>
                            <td class="text-center">${user.id}</td>
                            <td class="text-center">${user.username}</td>
                            <td class="text-center">${user.surname}</td>
                            <td class="text-center">${user.age}</td>
                            <td class="text-center">${user.email}</td>
                            <td class="text-center">${getRole(user)}</td>
                            <td class="text-center"><button class="btnUpdate btn btn-primary">Update</button></td>
                            <td class="text-center"><button class="btnDelete btn btn-danger">Delete</button></td>
                          </tr>
                         `
    })
    adminTable.innerHTML = resAdminTable
}

//Получаем роли
const getRole = (user) => {
    let userRoles = ''
    user.roles.forEach(role => userRoles += (role.name + ' '))
    return userRoles
}

//Роли для селекта
async function getRolesForSelect  () {
   const data = await fetch(urlAdmin +'roles').then(r => r.json()).catch(e => console.log(e))
    rolesSelect.options.length = 0
    data.forEach(item => {
        const op = document.createElement('option')
        op.value = item.id
        op.textContent = item.name
        rolesSelect.appendChild(op)
    })
}

// Открывается кнопка создания
btnCreate.addEventListener('click', () =>{
    username.value =''
    surname.value =''
    age.value =''
    email.value =''
    password.value =''
    rolesSelect.value = getRolesForSelect()
    option = 'create'
    modal.show()
})

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if(e.target.closest(selector)){
            handler(e)
        }
    })
}

// Удление юзера
on(document, 'click', '.btnDelete', e => {
    e.preventDefault()
    const dell = e.target.parentNode.parentNode
    const id = dell.firstElementChild.innerHTML
    fetch(urlAdmin + id, {
        method: 'DELETE'
    })
        .then(r => r.json())
        .then(() => {dell.remove()})
        .catch(e => console.log(e))
})


//Открываетс кнопка обновления
let idForm = 0
on(document, 'click', '.btnUpdate', e => {
    const up = e.target.parentNode.parentNode
    idForm = up.children[0].innerHTML
    const usernameForm = up.children[1].innerHTML
    const surnameForm = up.children[2].innerHTML
    const ageForm = up.children[3].innerHTML
    const emailForm = up.children[4].innerHTML

    username.value = usernameForm
    surname.value = surnameForm
    age.value = ageForm
    email.value = emailForm
    rolesSelect.value = getRolesForSelect()
    option = 'update'
    modal.show()
})

// создание и обновление юзера
form.addEventListener('submit', (e) => {
    e.preventDefault()
    if (option == 'create') {
        fetch(urlAdmin, {
            method:'POST',
            headers: {'Content-Type': 'application/json'},
            body:JSON.stringify({
                username: username.value,
                surname:surname.value,
                age:age.value,
                email:email.value,
                password:password.value,
                roles: Array.from(document.getElementById("rolesSelect").selectedOptions)
                    .map(option => ({ id: option.value, name: option.textContent} ))
            })
        })
            .then(r => r.json())
            .then(data => {
                const newData = []
                console.log(data)
                newData.push(data)
                console.log(newData)
                showAdminTableResult(newData)
            })
    }
    if (option == 'update') {
        fetch(urlAdmin + idForm, {
            method:'PUT',
            headers: {'Content-Type': 'application/json'},
            body:JSON.stringify({
                username: username.value,
                surname:surname.value,
                age:age.value,
                email:email.value,
                password:password.value,
                roles: Array.from(document.getElementById("rolesSelect").selectedOptions)
                    .map(option => ({ id: option.value, name: option.textContent} ))
            })
        })
            .then(r => r.json())
            .then(data => {
                console.log(data)
                const newData = []
                newData.push(data)
                console.log(newData)
                showAdminTableResult(newData)
            })
    }
    modal.hide()
})