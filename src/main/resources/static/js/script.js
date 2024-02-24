function updateSalary(e) {
    const employeeId = e.dataset.param1;
    fetch('/employees/' + employeeId + '/salary')
    .then(response => response.json())
    .then(data => {
        const employeeRow = document.querySelector('#employeeTable tr[data-id="' + employeeId + '"]');
        const salaryCell = employeeRow.querySelector('td[data-field="salary"]');
        salaryCell.innerText = data.salary;
    })
    .catch(error => console.error('Error updating salary:', error));
}

function copySalary(button) {
    const row = button.closest('tr');
    const salaryCell = row.querySelector('td[data-field="salary"]');
    const salaryValue = salaryCell.innerText.trim();

    const tempInput = document.createElement('input');
    tempInput.value = salaryValue;
    document.body.appendChild(tempInput);
    tempInput.select();
    document.execCommand('copy');
    document.body.removeChild(tempInput);
    alert('Salary copied: ' + salaryValue);
}
