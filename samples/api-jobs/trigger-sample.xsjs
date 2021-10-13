let job = new $.jobs.Job({
  uri: "demo/definition.xsjob"
});

// execute the job for 60 seconds, starting from now
job.configure({
  status: true,
  start_time: new Date(),
  end_time: new Date(Date.now() + 60000)
});