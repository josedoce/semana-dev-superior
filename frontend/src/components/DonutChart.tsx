import Chart from 'react-apexcharts';
import { BASE_URL } from 'utils/requests';
import axios from 'axios';
import { SaleSum } from 'types/sale';
import { useEffect, useState } from 'react';

type ChartData = {
  labels: string[],
  series: number[]
}

export function DonutChart(){
  //forma certa, com hooks
  const [chartData, setChartData] = useState<ChartData>({labels: [], series: []});
  useEffect(()=>{
    axios.get(`${BASE_URL}/sales/amount-by-seller`)
    .then(response =>{
      const data = response.data as SaleSum[];
      const chartData = {
        labels: data.map(e=>e.sellerName),
        series: data.map(e=>e.sum)
      };
      setChartData(chartData);
    });
  },[]);


  // //forma errada, sem hooks
  // let chartData : ChartData = {labels: [], series: []};
  // axios.get<SaleSum[]>(`${BASE_URL}/sales/amount-by-seller`)
  // .then(response =>{
  //   const data = response.data;
  //   chartData = {
  //     labels: data.map(e=>e.sellerName),
  //     series: data.map(e=>e.sum)
  //   };
  //   console.log(chartData);
  // });


  // const mockData = {
  //   series: [477138, 499928, 444867, 220426, 473088],
  //   labels: ['Anakin', 'Barry Allen', 'Kal-El', 'Logan', 'Padmé']
  // }
  
  const options = {
    legend: {
        show: true
    }
  }
  
  return(
    <Chart
      options={{...options, labels: chartData.labels}}
      series={chartData.series}
      type="donut"
      height="240"
    />
  );
}