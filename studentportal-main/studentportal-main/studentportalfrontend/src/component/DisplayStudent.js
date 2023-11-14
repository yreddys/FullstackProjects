import * as React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { useEffect, useState } from 'react';

const columns = [
  { field: 'id', headerName: 'ID', width: 70 },
  { field: 'name', headerName: 'NAME', width: 130 },
  { field: 'level', headerName: 'LEVEL', width: 130 },
];

export default function DisplayStudent() {
    const [studentsArray, setStudents] = useState([]);

    /**Here, we are fetching student from the api */
    useEffect(()=>{
        fetch("http://localhost:8080/student")
        .then(res=>res.json())
        .then(studentObj=>setStudents(studentObj))
        .catch(e=>console.log(e))
    },[studentsArray]);


  return (
    <div style={{ height: 300, width: '40%', margin:'auto', marginBottom:'55px', marginTop:'30px' }}>
        <h2> Student Data From Database</h2>
      <DataGrid
        rows={studentsArray}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[5]}
        checkboxSelection
      />
    </div>
  );
}
