import { useEffect, useState } from 'react';
import Chart from 'react-apexcharts';
import axios from 'axios';
import { BASE_URL } from 'utils/requests';
import { SaleSuccess } from 'types/sale';
import { round } from 'utils/format';

//site:https://apexcharts.com/
type SeriesData = {
  name: string;
  data: number[];
}
type ChartData = {
  labels: {
    categories: string[];
  };

  series: SeriesData[];
}

export function BarChart(){

  const [chartData, setChartData] = useState<ChartData>({
    labels: {categories: []},
    series: [{name: "",data: []}]
  });
  const options = {
    plotOptions: {
        bar: {
            horizontal: true,
        }
    },
  };
  
  useEffect(()=>{
    axios.get<SaleSuccess[]>(`${BASE_URL}/sales/success-by-seller`)
    .then(response=>{
      const data = response.data;
      const chartData: ChartData = {
        labels: {
          categories: data.map(e=>e.sellerName)
        },
        series: [
          {
            name: '% Success',
            data: data.map(e=>round(100.0 * e.deals / e.visited, 1))
          }
        ]
      }
      setChartData(chartData);
    });
  },[]);
  
  // const mockData = {
  //   labels: {
  //     categories: ['Anakin', 'Barry Allen', 'Kal-El', 'Logan', 'Padmé']
  //   },
  //   series: [
  //     {
  //       name: "% Sucesso",
  //       data: [43.6, 67.1, 67.7, 45.6, 71.1]                   
  //     }
  //   ]
  // };
  return(
    <Chart
      options={{...options, xaxis: chartData.labels}}
      series={chartData.series}
      type="bar"
      height="240"
    />
  );
}