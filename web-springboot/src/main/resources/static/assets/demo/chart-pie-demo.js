// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Pie Chart Example
let pie_ctx = document.getElementById("shibal_1");

let dbData = null;
fetch("http://localhost:9000/getCategoryCount")
    .then((res) => {
      res.json().then((jsonData) => {
        let _labels = []
        let _data = []

        jsonData.map((row) => {
          let rowData = JSON.parse(row);
          _labels.push(rowData["_id"]);
          _data.push(rowData["count"]);
        })

        console.log(_labels)
        console.log(_data)

        new Chart(pie_ctx, {
          type: 'pie',
          data: {
            labels: _labels,
            datasets: [{
              data: _data,
              backgroundColor: ['#efffba', '#dc3545', '#ffc107', '#28a745', '#ffafd8', '#f2cfa5'],
            }],
          },
        })
      })
    })
