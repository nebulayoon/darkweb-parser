// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
let bar_ctx = document.getElementById("myBarChart");

fetch("http://localhost:9000/getSiteNameCount")
    .then((res) => {
      res.json().then((jsonData) => {
        let _labels = []
        let _data = []

        jsonData.map((row) => {
          let rowData = JSON.parse(row);
          _labels.push(rowData["_id"]);
          _data.push(rowData["count"]);
        })

        const maxNum = Math.max.apply(null, _data);
        const maxCeil = Math.ceil(maxNum/100)*100;

        new Chart(bar_ctx, {
          type: 'bar',
          data: {
            labels: _labels,
            datasets: [{
              label: "Revenue",
              backgroundColor: "rgba(202,166,254,1)",
              borderColor: "rgba(2,117,216,1)",
              data: _data,
            }],
          },
          options: {
            scales: {
              xAxes: [{
                time: {
                  unit: 'site'
                },
                gridLines: {
                  display: false
                },
                ticks: {
                  maxTicksLimit: 6
                }
              }],
              yAxes: [{
                ticks: {
                  min: 0,
                  max: maxCeil,
                  maxTicksLimit: 5
                },
                gridLines: {
                  display: true
                }
              }],
            },
            legend: {
              display: false
            }
          }
        });
      })
    })

// var myLineChart = new Chart(bar_ctx, {
//   type: 'bar',
//   data: {
//     labels: ["January", "February", "March", "April", "May", "June"],
//     datasets: [{
//       label: "Revenue",
//       backgroundColor: "rgba(2,117,216,1)",
//       borderColor: "rgba(2,117,216,1)",
//       data: [4215, 5312, 6251, 7841, 9821, 14984],
//     }],
//   },
//   options: {
//     scales: {
//       xAxes: [{
//         time: {
//           unit: 'month'
//         },
//         gridLines: {
//           display: false
//         },
//         ticks: {
//           maxTicksLimit: 6
//         }
//       }],
//       yAxes: [{
//         ticks: {
//           min: 0,
//           max: 15000,
//           maxTicksLimit: 5
//         },
//         gridLines: {
//           display: true
//         }
//       }],
//     },
//     legend: {
//       display: false
//     }
//   }
// });
