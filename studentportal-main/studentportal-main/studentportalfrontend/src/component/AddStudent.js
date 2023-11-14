import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { useState } from 'react';

export default function AddStudent() {
    const [name, setName] = useState("");
    const [level, setLevel] = useState("");

    const submitData =(e) =>{
        e.preventDefault();
        const studentData = {name,level};
        /**Here, we are using a post request to send data to the api */
        fetch("http://localhost:8080/student/add",
        {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body:JSON.stringify(studentData)
        })
        .then(res=>console.log(res))
        .catch(e=>console.log(e))
    };
  return (
      <div>
          <h1>Add Student</h1>
      <Box
        component="form"
        sx={{
          '& > :not(style)': { m: 0.5, width: '50%' },
        }}
        noValidate
        autoComplete="off"
      >
        <TextField label="name" variant="outlined" onChange={(e)=>setName(e.target.value)} />
        <TextField label="level" variant="outlined" onChange={(e)=>setLevel(e.target.value)} />
        <Button onClick={submitData} style={{padding:"14px"}} variant="contained">Add Student</Button>
  
      </Box>

      </div>
  );
}
