const url = 'http://localhost:8080/user'
const userTable = document.querySelector('tbody')
let result = ''

const showTable = (users) => {
    users.forEach(user => {
        result +=
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.surname}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${user.roles}</td>
                    </tr>
    })
    userTable.innerHTML = result
}

fetch(url)
    .then(response => response.json())