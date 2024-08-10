const url = "http://localhost:8080/api/user";
const tableBody = document.getElementById('userTable');
const span = document.getElementById('span')
let result = '';
let resultSpan = '';


async function showElemUserTable() {
    const user = await fetch(url).then(r => r.json().catch(e => console.log(e)));
    result += `<tr>
                  <td>${user.id}</td>
                  <td>${user.username}</td>
                  <td>${user.surname}</td>
                  <td>${user.age}</td>
                  <td>${user.email}</td>
                  <td>${getRole(user)}</td>
               </tr>`
    tableBody.innerHTML = result
}

const getRole = (user) => {
    let userRoles = ''
    user.roles.forEach(role => userRoles += (role.name + ' '))
    return userRoles
}

async function showNavElement () {
    const user = await fetch(url).then(r => r.json().catch(e => console.log(e)));
    resultSpan += `<h5 class="text-white bg-dark">
                    <td>${user.email}</td>
                    <br>
                    with roles:
                    <td>${getRole(user)}</td>`
    span.innerHTML = resultSpan
}
showNavElement().then(r => showElemUserTable())
